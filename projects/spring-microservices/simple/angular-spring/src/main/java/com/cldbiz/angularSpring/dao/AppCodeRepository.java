package com.cldbiz.angularSpring.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cldbiz.angularSpring.domain.AppCode;

@Repository
public interface AppCodeRepository extends CrudRepository<AppCode, Long> {

}
