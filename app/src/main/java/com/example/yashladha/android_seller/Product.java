package com.example.yashladha.android_seller;

/**
 * Created by dell pc on 21-10-2017.
 */

public class Product {

    private String mProductName;
    private String mDescription;
    private String mOriginalPrice;
    private String mNewPrice;
    private String mDiscount;
    private String mExchange;
    private String mYesNo;

    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;

    public Product(String mProductName, String mDescription, String mOriginalPrice, String mNewPrice,
                   String mDiscount, String mExchange, int mImageResourceId,String mYesNo) {
        this.mProductName = mProductName;
        this.mDescription = mDescription;
        this.mOriginalPrice = mOriginalPrice;
        this.mNewPrice = mNewPrice;
        this.mDiscount = mDiscount;
        this.mExchange = mExchange;
        this.mImageResourceId = mImageResourceId;
        this.mYesNo = mYesNo;
    }

    public String getmProductName(){return mProductName;}
    public String getmDescription(){return mDescription;}
    public String getmOriginalPrice(){return mOriginalPrice;}
    public String getmNewPrice(){return mNewPrice;}
    public String getmDiscount(){return mDiscount;}
    public String getmExchange(){return mExchange;}
    public String getmYesNo(){return mYesNo;}

    public int getmImageResourceId(){return mImageResourceId;}

    public boolean hasImage()
    {
        boolean result = mImageResourceId!=NO_IMAGE_PROVIDED;
        return result;
    }

}
