package com.example.swlee.webapp; /**
 * Created by swlee on 2017. 9. 1..
 */

import android.content.ContentValues;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RequestHttpURLPost {

    // tag
    private static final String Tag  = "RequestHttpURL";

    public String request(String _url, ContentValues _params){

        // HttpURLConnection 참조 변수.
        HttpURLConnection urlConn = null;
        // URL 뒤에 붙여서 보낼 파라미터.
        StringBuffer sbParams = new StringBuffer();

        /**
         * 1. StringBuffer에 파라미터 연결
         * */
        // 보낼 데이터가 없으면 파라미터를 비운다.
        if (_params == null){
            sbParams.append("");
        }
        else {
            // 보낼 데이터가 있으면 파라미터를 채운다.
            // 파라미터가 2개 이상이면 파라미터 연결에 &가 필요하므로 스위칭할 변수 생성.
            boolean isAnd = false;
            // 파라미터 키와 값.
            String key;
            String value;

            for(Map.Entry<String, Object> parameter : _params.valueSet()){
                key = parameter.getKey();
                value = parameter.getValue().toString();

                // 파라미터가 두개 이상일때, 파라미터 사이에 &를 붙인다.
                if (isAnd)
                    sbParams.append("&");

                sbParams.append(key).append("=").append(value);

                // 파라미터가 2개 이상이면 isAnd를 true로 바꾸고 다음 루프부터 &를 붙인다.
                if (!isAnd)
                    if (_params.size() >= 2)
                        isAnd = true;
            }
        }

        /**
         * 2. HttpURLConnection을 통해 web의 데이터를 가져온다.
         * */
        try{
            URL url = new URL(_url);
//            Log.d(Tag,"getHost is " +url.getHost());
//            Log.d(Tag,"getProtocol is " +url.getProtocol());
//            Log.d(Tag,"getPort is " + String.valueOf(url.getPort()));
//            URL url = new URL("http",_url,3000,"");
            urlConn = (HttpURLConnection) url.openConnection();
            Log.d(Tag,"responsecode is " +urlConn.getResponseCode());

            // [2-1]. urlConn 설정.
            urlConn.setRequestMethod("GET"); // URL 요청에 대한 메소드 설정 : POST.
//            urlConn.setDoOutput(true); //-- https://stackoverflow.com/questions/13247836/android-java-net-protocolexception-does-not-support-output
//            urlConn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
//            urlConn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");

            // [2-2]. parameter 전달 및 데이터 읽어오기.
            String strParams = sbParams.toString(); //sbParams에 정리한 파라미터들을 스트링으로 저장. 예)id=id1&pw=123;
            OutputStream os = urlConn.getOutputStream();
            os.write(strParams.getBytes("UTF-8")); // 출력 스트림에 출력.
            os.flush(); // 출력 스트림을 플러시(비운다)하고 버퍼링 된 모든 출력 바이트를 강제 실행.
            os.close(); // 출력 스트림을 닫고 모든 시스템 자원을 해제.

            // [2-3]. 연결 요청 확인.
            // 실패 시 null을 리턴하고 메서드를 종료.
//            ((HttpURLConnection) url.openConnection()).getResponseCode()
            if (urlConn.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }

            // [2-4]. 읽어온 결과물 리턴.
            // 요청한 URL의 출력물을 BufferedReader로 받는다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));


            // 출력물의 라인과 그 합에 대한 변수.
            String line;
            String page = "";

            // 라인을 받아와 합친다.
            while ((line = reader.readLine()) != null){
//                Log.d(Tag,"tocken is " +Token);
//                Log.d(Tag, "network"+ line);
                page += line;
            }

            return page;

        } catch (MalformedURLException e) { // for URL.
            e.printStackTrace();
        } catch (IOException e) { // for openConnection().
            e.printStackTrace();
        } finally {
            if (urlConn != null)
                urlConn.disconnect();
        }

        return null;

    }

}
