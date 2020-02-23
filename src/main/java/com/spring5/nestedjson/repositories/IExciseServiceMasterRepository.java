package com.spring5.nestedjson.repositories;

import com.spring5.nestedjson.models.ExciseServiceMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExciseServiceMasterRepository extends JpaRepository<ExciseServiceMaster, Long> {

}