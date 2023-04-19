package com.hdel.common.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

@Service
public class ConverterUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConverterUtil.class);

    public static String xml2JsonString(String xml) throws Exception {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);

        return xmlJSONObj.toString();
    }

    public static HashMap<String, Object> jsonString2Map (String jsonString) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return (HashMap<String, Object>) mapper.readValue(jsonString, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<List<String>> readToList(String path) {
        List<List<String>> list = new ArrayList<List<String>>();
        File csv = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8");
            String line = "";

            while((line=br.readLine()) != null) {
                String[] token = line.split(",");
                List<String> tempList = new ArrayList<String>(Arrays.asList(token));
                list.add(tempList);
            }

        } catch (FileNotFoundException e) {
            LOGGER.error(String.valueOf(e));
        } catch (IOException e) {
            LOGGER.error(String.valueOf(e));
        } finally {
            try {
                if(br != null) {br.close();}
            } catch (IOException e) {
                LOGGER.error(String.valueOf(e));
            }
        }
        return list;
    }

}
