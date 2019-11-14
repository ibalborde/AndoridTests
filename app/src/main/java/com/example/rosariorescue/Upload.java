package com.example.rosariorescue;

import java.util.List;

public class Upload {
    private String mName;
    private List<String> mImageUrlList;
    private String mImageUrl;
    private int mStatus;
    private int mType;
    private String mDescription;


    public Upload(){

    }

    public Upload(String name, String imageUrl, int status, int type, String description){
        mName = name;
        mImageUrl = imageUrl;
        //mImageUrlList = imageUrlList;
        mStatus = status;
        mType = type;
        mDescription = description;
    }


    //getter

    public String getName(){
        return mName;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

//    public List<String> getImageUrlList(){
//        return mImageUrlList;
//    }

    public int getStatus(){
        return mStatus;
    }

    public int getType(){
        return mType;
    }

    public String getDescription(){
        return mDescription;
    }

    //setters

    public void setName(String name){
        mName = name;
    }

    public void setImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }

//    public void setImageUrlList(List<String> ImageUrlList){
//        mImageUrlList = ImageUrlList;
//
//    }

    public void setStatus(int status){
        mStatus = status;
    }

    public void setType(int type){
        mType = type;
    }

    public void setDescription(String description){
        mDescription = description;
    }






}
