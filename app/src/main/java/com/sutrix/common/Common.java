package com.sutrix.common;

import com.sutrix.model.Truyen;

public class Common {

    private static String DB_NAME = "doctruyen";
    private static String COLLECTION_NAME = "truyen";
    public static String API_KEY = "ngOeir8rM1V4RKtY6s_-46mCrPERHsHZ";

    public static String getAddressSingle(String oid){

        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("/"+oid+"?apiKey="+API_KEY);

        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("?apiKey="+API_KEY);

        return stringBuilder.toString();
    }

    public static String getAddressWithTheLoai(String theloai){

        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("?apiKey="+API_KEY);

        stringBuilder.append("&q={'TheLoai':'"+theloai+"'}");

        return stringBuilder.toString();
    }

    public static String getAddressWithLimit(int limitTruyen){

        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("?apiKey="+API_KEY);

        stringBuilder.append("&l="+limitTruyen);

        return stringBuilder.toString();
    }
}
