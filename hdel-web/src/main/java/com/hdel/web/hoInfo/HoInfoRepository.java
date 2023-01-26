package com.hdel.web.hoInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoInfoRepository extends JpaRepository<HoInfo, Long> {
    public List<HoInfo> findHoInfoByProjNo(String projNo);

    //public List<HoInfo> findById(Long id);

}
