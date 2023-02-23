package com.hdel.web.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HdelUserRepository extends JpaRepository<GovElevatorInfo, Long> {
//    Optional<HdelUser> findByEmail(String email);
//    boolean existsByEmail(String email);
}
