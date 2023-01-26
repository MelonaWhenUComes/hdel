package com.hdel.web.mapper;

import com.hdel.web.hoInfo.HoInfo;
//import org.mybatis.spring.annotation.MapperScan;


//Mybatis Mapper
//@Component
//@MapperScan

public interface TestMapper {
    HoInfo hoInfo() throws Exception;
}
