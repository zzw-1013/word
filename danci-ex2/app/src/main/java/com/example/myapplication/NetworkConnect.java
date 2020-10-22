package com.example.myapplication;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkConnect {
    public final static String iCiBaURL1="http://dict-co.iciba.com/api/dictionary.php?w=";
    public final static String iCiBaURL2="&key=BDC37F4ACF9FB5F102A8B1EFA3C0263E";

    public static InputStream getInputStreamByUrl(String urlStr){
        InputStream tempInput=null;
        URL url=null;
        HttpURLConnection connection=null;
        //设置超时时间

        try{
            url=new URL(urlStr);
            connection=(HttpURLConnection)url.openConnection();     //别忘了强制类型转换
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(10000);
            tempInput=connection.getInputStream();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tempInput;
    }
}
