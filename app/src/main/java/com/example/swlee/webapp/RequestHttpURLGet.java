package com.example.swlee.webapp; /**
 * Created by swlee on 2017. 9. 1..
 */

import android.content.ContentValues;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

// example 성공
public class RequestHttpURLGet {

    // tag
    private static final String Tag  = "RequestHttpURL";

    //
    private StringBuilder output = new StringBuilder();


    public String getExam(String param_url) throws IOException {

        URL url = new URL(param_url);
        // 문자열로 URL 표현
        System.out.println("get exam start" + url.toExternalForm());

        System.out.println("URL :" + url.toExternalForm());

        // HTTP Connection 구하기
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
        try {
            conn.setRequestMethod("GET");
            // 연결 타임아웃 설정
            conn.setConnectTimeout(3000); // 3초
            // 읽기 타임아웃 설정
            conn.setReadTimeout(3000); // 3초
            // 이두줄때문에 안되네  이유가  머지 -- 현재는 안사용 하는듯
            //            conn.setDoInput(true);
            //            conn.setDoOutput(true);
            // 요청 방식 구하기
            System.out.println("getRequestMethod():" + conn.getRequestMethod());
            // 응답 콘텐츠 유형 구하기
            System.out.println("getContentType():" + conn.getContentType());
            // 응답 코드 구하기
            System.out.println("getResponseCode():"    + conn.getResponseCode());
            // 응답 메시지 구하기
            System.out.println("getResponseMessage():" + conn.getResponseMessage());
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        // 응답 헤더의 정보를 모두 출력
        for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
            for (String value : header.getValue()) {
                System.out.println(header.getKey() + " : " + value);
            }
        }

        //연결 된경우
        if (conn.getResponseCode()== HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;

            while(true) {
                line = reader.readLine();

                if (line == null){
                    break;
                }

                output.append(line + "\n");
            }
            reader.close();
        }

        // 접속 해제
        conn.disconnect();
        System.out.println("get exam end" + url.toExternalForm());
        return output.toString();
    }

}
