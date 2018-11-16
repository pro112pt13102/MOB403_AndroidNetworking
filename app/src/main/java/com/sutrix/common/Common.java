package com.sutrix.common;

import com.sutrix.model.Truyen;

public class Common {

    private static String DB_NAME = "doctruyen";
    private static String COLLECTION_NAME = "truyen";
    public static String API_KEY = "ngOeir8rM1V4RKtY6s_-46mCrPERHsHZ";

    public static String getAddressSingle(Truyen truyen){

        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("/"+truyen.get_id().getOid()+"?apiKey="+API_KEY);

        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);

        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("?apiKey="+API_KEY);

        return stringBuilder.toString();
    }
}
