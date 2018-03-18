package com.example.yashladha.android_seller;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dell pc on 21-10-2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private int mColorResourceId;

    public ProductAdapter(Activity context, ArrayAdapter<Product> products, int colorResourceId) {
        super(context,0,products);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }
        Word currentWord = getItem(position);

        TextView defaultTranslation = (TextView)listItemView.findViewById(R.id.default_text_View);
        defaultTranslation.setText(currentWord.getDefaultTranslation());

        TextView miwokTranslation = (TextView)listItemView.findViewById(R.id.mivok_text_View);
        miwokTranslation.setText(currentWord.getMiwokTranslation());

        ImageView imageView = (ImageView)listItemView.findViewById(R.id.imageView);
        if(currentWord.hasImage())
        {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;

    }
}
