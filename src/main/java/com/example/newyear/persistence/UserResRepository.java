package com.example.newyear.persistence;

import com.example.newyear.persistence.UserRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResRepository extends JpaRepository<UserRes, Long> {
}