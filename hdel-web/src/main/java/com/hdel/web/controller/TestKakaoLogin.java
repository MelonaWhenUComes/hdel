package com.hdel.web.controller;

//import com.fasterxml.jackson.core.JsonParser;

import com.hdel.web.service.common.ConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


@RestController
@CrossOrigin
@RequestMapping("/kakaoApi")
public class TestKakaoLogin {
    private String kakaoRestApiKey = "2ebc16cf71ceca131836536e7d42a964";
    public static final Logger logger = LoggerFactory.getLogger(TestKakaoLogin.class);

    /***********************************************************************/
    /**
        1. getAuthCode 에서 Access 토큰 가져옴 -> Front 로 redirect 시킴 (세션 없을시 카카오 로그인 수행)
        2. getToken 에서 Access Token 생성
        3. 발행된 Access Token 으로 Kakao로부터 사용자정보 가져오기
        4. Kakao인수한 사용자정보를 통하여 Legacy DB 찾기 (return Value : email, id ) > 전화번호로 찾을수 있는지
            *** id 로 사용이 가능한지
        5. Legacy DB 의 email 정보가 없을 경우 회원가입 수행
        6. id or email 존재시 Legacy Session 맺기
     **/
    /***********************************************************************/

    @GetMapping("/getAuthCode")
    public ResponseEntity<String> getAuthCode() {
        String accessKeyUrl = "";

        accessKeyUrl += "https://kauth.kakao.com/oauth/authorize?";
        accessKeyUrl += "client_id=" + kakaoRestApiKey;
        accessKeyUrl += "&redirect_uri=http://localhost:18080/kakaoApi/getToken";
        accessKeyUrl += "&response_type=code";

        return new ResponseEntity<String> (accessKeyUrl, HttpStatus.OK);
    }

    @GetMapping("/getToken")
    public String getToken(@RequestParam(value="code")String code) throws UnsupportedEncodingException {
        //사용자 정보 가져오기
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String result = "";
        String userInfoRes = "";

        HttpURLConnection conn = null;
        try {
            URL url = new URL(reqURL);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + kakaoRestApiKey);
            sb.append("&redirect_uri=http://localhost:18080/kakaoApi/getToken");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            logger.warn("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            logger.warn("response body : " + result);

            ConverterUtil converterUtil = new ConverterUtil();
            HashMap<String, Object> map = converterUtil.jsonString2Map(result);

            String access_token = String.valueOf(map.get("access_token"));
            logger.warn("access_token : " + access_token);

            /** 사용자 정보 가져오기 **/
            HashMap<String, Object> infoMap = new HashMap<>();
            infoMap = converterUtil.jsonString2Map(this.getUserInfo(access_token));

            logger.warn("Info Map : " + infoMap);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return "result : " + userInfoRes ;
    }

    /*** 개인정보 Get **/
    public String getUserInfo(String access_token) {
        String result = "";
        HttpURLConnection conn = null;
        try {
            String userInfoURL = "https://kapi.kakao.com/v2/user/me";
            URL url = new URL(userInfoURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int userRespCode = conn.getResponseCode();
            logger.warn("userRespCode : " + userRespCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
            return result;
        }
    }

}



