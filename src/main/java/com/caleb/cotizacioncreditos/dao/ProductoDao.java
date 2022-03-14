package com.caleb.cotizacioncreditos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.caleb.cotizacioncreditos.entity.ProductoEntity;

public interface ProductoDao extends JpaRepository<ProductoEntity, String>{

	@Query("select u from ProductoEntity u where u.sku like %:cadena% or u.nombre like %:cadena%")
	List<ProductoEntity> findByKeyWord(@Param("cadena") String palabraClave);
}
