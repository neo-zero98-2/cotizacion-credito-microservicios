package com.caleb.cotizacioncreditos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caleb.cotizacioncreditos.entity.PlazoEntity;

public interface PlazoDao extends JpaRepository<PlazoEntity,String>{

}
