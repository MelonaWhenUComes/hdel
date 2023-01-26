package com.hdel.web.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdel.web.dto.api.NaverNews;
import com.hdel.web.service.common.ApiHttpRequest;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
@Service
public class NaverNewsApiService {
    @Value("${app.api.X-Naver-Client-Id}")
    private String NAVER_API_ID;

    @Value("${app.api.X-Naver-Client-Secret}")
    private String NAVER_API_SECRET;

    @Value("${app.api.Naver-News-URI}")
    private String NAVER_NEWS_API_URI;

    ApiHttpRequest apiHttpRequest;

    public List<NaverNews> getNaverNews(String queryParam) {
        List<NaverNews> naverNewsList = new ArrayList<>();

        String queryText= queryParam;
        String queryEncode = "";
        String displayCount = "100";


        try {
            queryEncode = URLEncoder.encode(queryText, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to Encode.");
        }

        String apiURI = NAVER_NEWS_API_URI + "?query=" + queryEncode + "&display=" + displayCount;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", NAVER_API_ID);
        requestHeaders.put("X-Naver-Client-Secret", NAVER_API_SECRET);

        //Call API
        String responseBody = apiHttpRequest.get(apiURI,requestHeaders);

        System.out.println(responseBody);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<Object> tempList = null;

        try {
            tempList = objectMapper.readValue( responseBody, new TypeReference<List<Object>>() {} );
            //BeanUtils.copyProperties(tempList, naverNewsList);
            if(tempList != null) {
                List<Map<String, Object>> itemList = new ArrayList<>();

                itemList = (List<Map<String, Object>>) ((LinkedHashMap) tempList.get(0)).get("items");

                for(Map<String, Object> map : itemList) {
                    NaverNews naverNews = new NaverNews();

                    naverNews.setTitle((String)map.get("title"));
                    naverNews.setDescription(((String)map.get("description")));
                    naverNews.setLink(((String)map.get("link")));
                    naverNews.setPubdate(((String)map.get("pubdate")));

                    naverNewsList.add(naverNews);

                }
            }

        } catch ( IOException e ) {
            throw new RuntimeException( e );
        } catch(Exception e) {
            throw new RuntimeException( e );
        }

        return naverNewsList;
    }

}
