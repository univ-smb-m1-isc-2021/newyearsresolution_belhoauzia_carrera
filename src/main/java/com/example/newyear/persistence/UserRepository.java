package com.example.newyear.persistence;

import com.example.newyear.persistence.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserClass, Long> {
    UserClass findByUsername(String username);
}