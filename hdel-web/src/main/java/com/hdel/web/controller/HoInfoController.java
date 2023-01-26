package com.hdel.web.controller;

import com.hdel.web.hoInfo.HoInfo;
import com.hdel.web.service.HoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hoInfo")
@CrossOrigin
public class HoInfoController {
    @Autowired
    HoInfoService hoInfoService;
    //private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/getHoInfoAll", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<HoInfo>> getHoInfoAll() {
        List<HoInfo> hoInfos = hoInfoService.findAll();

        List<HoInfo> hoInfos1 = new ArrayList<>();

        for(int i = 0; i < 1000 ; i++) {
            hoInfos1.add(hoInfos.get(i));
        }
        return new ResponseEntity<List<HoInfo>>(hoInfos1, HttpStatus.OK);
    }

}
