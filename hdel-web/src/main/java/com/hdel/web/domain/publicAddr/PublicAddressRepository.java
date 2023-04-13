package com.hdel.web.domain.publicAddr;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublicAddressRepository extends JpaRepository<PublicAddress, Long> {

}