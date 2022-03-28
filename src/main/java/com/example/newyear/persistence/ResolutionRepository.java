package com.example.newyear.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {

    Resolution findByID(long id);
    //Resolution findALL(long id);

}
