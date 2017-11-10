package com.example.yashladha.android_seller;

import android.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yashladha.android_seller.helper.EmailHelper;
import com.example.yashladha.android_seller.helper.HelperDef;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RegistrationActivity extends AppCompatActivity {
    public static final String TITLE = "Register";
    TextView tvName, tvPassword, tvLogin;
    EditText etPassword, etEmail;
    boolean ans2 = false;
    Button btLogin, btFacebook, btGoogle, btCheckEmail;
    ImageButton ibPassword;
    boolean password2 = false;
    String name = "";
    ImageView ivProfile;
    TextView tvAddPic;
    String UID_i = "";
    String password = "";
    private int GALLERY = 1, CAMERA = 2;
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
    static Date now = new Date();
    private static final String IMAGE_DIRECTORY = "/hatsphere/Seller" + formatter.format(now);


    private RequestQueue rq;
    String email = "";
    private Button btRegister;

    public RegistrationActivity() {
        // Required empty public constructor
    }

    public static RegistrationActivity newInstance() {
        return new RegistrationActivity();
    }

    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ivProfile = (ImageView) findViewById(R.id.ivAdd);
        tvAddPic = (TextView) findViewById(R.id.tvAdd);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btLogin = (Button) findViewById(R.id.btLogin);
        btFacebook = (Button) findViewById(R.id.btFacebook);
        btCheckEmail = (Button) findViewById(R.id.btCheckEmail);
        rq = Volley.newRequestQueue(RegistrationActivity.this);
        btGoogle = (Button) findViewById(R.id.btGoogle);
        ibPassword = (ImageButton) findViewById(R.id.ibPassword);
        btRegister = btLogin;
        ans2 = false;

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
                tvAddPic.setText("");
                ivProfile.setClickable(false);
            }
        });
        btCheckEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj2 = new JSONObject();
                try {
                    obj2.put("email", email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                        Request.Method.POST, "http://10.0.2.2:3000/user/check/email/", obj2, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("email check", response.toString());
                            if (response.get("code").toString().equals("201")) {
                                Toast.makeText(RegistrationActivity.this, "Email already used in registration", Toast.LENGTH_LONG).show();
                            } else {
                                ans2 = true;
                                Toast.makeText(RegistrationActivity.this, "Email not used in registration", Toast.LENGTH_LONG).show();
                                btRegister.setEnabled(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.toString());
                        Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                rq.add(jsonObjectRequest2);

            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tempEmail = email;
                boolean ans = validateUserName() && validatePassword() && ans2;
                if (password.length() < 6) {
                    etPassword.setError("Password length should be greater then 6 characters");
                } else {
                    if (ans) {
                        final JSONObject obj = new JSONObject();
                        try {
                            obj.put("email", email);
                            obj.put("password", password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.POST, "http://10.0.2.2:3000/user/signUp/", obj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.get("response").toString().equals("200")) {
                                        UID_i = response.getString("uid");
                                        obj.put("uid", response.get("uid"));
                                        JsonObjectRequest dataPushRequest = new JsonObjectRequest(
                                                Request.Method.POST,
                                                "http://10.0.2.2:3000/user/push/seller",
                                                obj,
                                                new Response.Listener<JSONObject>() {
                                                    @Override
                                                    public void onResponse(JSONObject response) {
                                                        try {
                                                            if (response.get("response").toString().equals("200")) {
                                                                Toast.makeText(RegistrationActivity.this, response.get("response").toString(), Toast.LENGTH_SHORT).show();
                                                                SavePreference();
                                                                btLogin.setEnabled(false);
                                                                Toast.makeText(RegistrationActivity.this, UID_i, Toast.LENGTH_SHORT).show();
                                                                startIntent();
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Log.e("onErrorResponse", error.getMessage());
                                                    }
                                                }
                                        );
                                        rq.add(dataPushRequest);
                                    } else {
                                        Log.d("500 res", "Response catches " + tempEmail);
                                        HelperDef.getUID(tempEmail, RegistrationActivity.this, new EmailHelper() {
                                            @Override
                                            public void getUID(JSONObject res, Context context) {
                                                try {
                                                    Log.d("Response", res.toString());
                                                    UID_i = res.getString("uid");
                                                    Log.d("UID", UID_i);
                                                    SavePreference();
                                                    startIntent();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("error", error.toString());
                                Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        rq.add(jsonObjectRequest);
                        etPassword.setText("");
                        etEmail.setText("");
                    }
                }
            }
        });


        ibPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password2 == false)
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password2 = !password2;

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = etPassword.getText().toString();


            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = etEmail.getText().toString().trim();
            }
        });
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etEmail.getText().toString().trim().length() < 5) {
                        etEmail.setError("Minimum length should be 5 characters");
                    } else {
                        etEmail.setError(null);
                    }
                }
            }
        });


        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (etPassword.getText().toString().trim().length() < 8) {
                        etPassword.setError("Minimum length should be 8 characters");
                    } else {
                        etPassword.setError(null);
                    }
                }
            }
        });

        btFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void startIntent() {
        Intent i = new Intent(RegistrationActivity.this, RegisterActivity_2.class);
        startActivity(i);
    }

    private void SavePreference() {
        SharedPreferences.Editor editor = getSharedPreferences("myprfs", MODE_PRIVATE).edit();
        editor.putString("UID", UID_i);
        editor.commit();
        editor.apply();
    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().trim().isEmpty()) {

            Toast.makeText(RegistrationActivity.this, "Invalid Password",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {

        }
        return true;
    }

    private boolean validateUserName() {
        if (email.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "Invalid User Name",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (!email.isEmpty() && !isValidEmail(email)) {
            Toast.makeText(RegistrationActivity.this, "Invalid User Name",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {

        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(RegistrationActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    ivProfile.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegistrationActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            saveImage(thumbnail);
            ivProfile.setImageBitmap(thumbnail);
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            isStoragePermissionGranted();
            wallpaperDirectory.createNewFile();
            Log.d("TAG", "File Saved::--->" + wallpaperDirectory.getAbsolutePath());
            //Log.d("TAG", image.toString());

            return wallpaperDirectory.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Tag", "Permission is granted");
                return true;
            } else {

                Log.v("Tag", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Tag", "Permission is granted");
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("Tag", "Permission: " + permissions[0] + "was " + grantResults[0]);
            //resume tasks needing this permission
        }
    }

}