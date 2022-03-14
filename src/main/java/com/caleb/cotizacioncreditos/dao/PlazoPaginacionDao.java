package com.caleb.cotizacioncreditos.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.caleb.cotizacioncreditos.entity.PlazoEntity;

public interface PlazoPaginacionDao extends PagingAndSortingRepository<PlazoEntity, String> {

}
