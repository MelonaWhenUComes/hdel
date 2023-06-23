package com.hdel.web.controller.openApi;

import com.hdel.web.dto.api.NaverNews;
import com.hdel.web.service.api.NaverNewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/openApi")
@CrossOrigin
public class NaverNewsController {
    @Autowired
    NaverNewsApiService naverNewsApiService;
    //private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/naver/getNews/{query}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<NaverNews>> getNaverNews(@PathVariable("query") String queryParam) {
    //public ResponseEntity<List<NaverNews>> getNaverNews(String param) {
        List<NaverNews> naverNews = naverNewsApiService.getNaverNews(queryParam);

        return new ResponseEntity<List<NaverNews>>(naverNews, HttpStatus.OK);
    }

}
