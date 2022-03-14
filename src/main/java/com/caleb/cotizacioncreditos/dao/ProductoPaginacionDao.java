package com.caleb.cotizacioncreditos.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.caleb.cotizacioncreditos.entity.ProductoEntity;

public interface ProductoPaginacionDao extends PagingAndSortingRepository<ProductoEntity, String> {
	
}
