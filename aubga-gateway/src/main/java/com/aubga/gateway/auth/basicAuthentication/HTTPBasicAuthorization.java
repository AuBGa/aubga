package com.aubga.gateway.auth.basicAuthentication;
import com.aubga.gateway.auth.basicAuthentication.base64.Base64;

import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPBasicAuthorization {

    /**
     * HTTP Basic Authentication
     *
     * @param address
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static int connection(String address,String username,String password)
            throws Exception{
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        String author = "Basic " + Base64.encodeBase64((username + ":" + password).getBytes());
        conn.setRequestProperty("Authorization", author);
        conn.connect();
        return conn.getResponseCode();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            connection("http://192.168.0.1","admin","admin123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}