package com.sutrix.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPDataHandler {

    public static String stream = null;

    public HTTPDataHandler() {
    }

    public String getHTTPData(String urlString){
        try {

            URL url = new URL(urlString);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();



                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();

                String line;

                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line);
                }

                stream = stringBuilder.toString();
                httpURLConnection.disconnect();


        }catch (MalformedURLException e){

            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }

        return stream;
    }
}
