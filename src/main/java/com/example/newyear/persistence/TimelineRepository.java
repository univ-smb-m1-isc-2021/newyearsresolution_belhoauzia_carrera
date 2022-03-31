package com.example.newyear.persistence;

import com.example.newyear.persistence.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TimelineRepository extends JpaRepository<Timeline, Date> {
}