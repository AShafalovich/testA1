package com.ashafalovich.testApplication.repository;

import com.ashafalovich.testApplication.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findAllByAppAccountNameAndActive(String appAccountName, boolean isActive);
}
