package com.example.newyear.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserResRepository extends JpaRepository<UserRes, Long> {
    ArrayList<UserRes> findByUser(UserClass u);
}