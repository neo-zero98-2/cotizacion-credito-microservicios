package com.caleb.cotizacioncreditos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caleb.cotizacioncreditos.dao.ProductoDao;
import com.caleb.cotizacioncreditos.dao.ProductoPaginacionDao;
import com.caleb.cotizacioncreditos.entity.ProductoEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductoService {

	@Autowired
	private ProductoDao repo;
	
	@Autowired
	private ProductoPaginacionDao repoPaginacion;
	
	public List<ProductoEntity> getProductos() throws Exception{
		List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
		try {
			 productos = this.repo.findAll();
			log.info("Se obtuvieron todos los productos");
			return productos;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al obtener los productos");
			throw new Exception("Error al obtener los productos "+e.getMessage());
		}
	}
	
	public List<ProductoEntity> getProductosP(
			Integer pageNo, Integer pageSize, String sortBy
			) throws Exception{
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<ProductoEntity> pagedResult = repoPaginacion.findAll(paging);
			return pagedResult.getContent();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al obtener los productos paginados");
			throw new Exception("Error al obtener los productos paginados"+e.getMessage());
		}
		
	}
	
	public void addProduct(ProductoEntity producto) throws Exception {
		try {
			this.repo.save(producto);
			log.info("Se agrego el producto");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al agregar productos ");
			throw new Exception("Error al agregar o actualiza producto "+e.getMessage());
		}
	}
	
	public ProductoEntity findById(String id) throws Exception {
		Optional<ProductoEntity> opt;
		ProductoEntity producto;
		try {
			opt = this.repo.findById(id);
			if(opt.isPresent()) {
				log.info("Se Obtuvo el producto por id");
				producto = opt.get();
			}else {
				log.info("No se Obtuvo el producto por id");
				producto = null;
			}
			return producto;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al obtener producto por id ");
			throw new Exception("Error al obtener el producto por id "+e.getMessage());
		}
	}
	
	
	
	public void deleteProduct(String sku) throws Exception {
		try {
			this.repo.deleteById(sku);
			log.info("Se limino el producto");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al eliminar productos");
			throw new Exception("Error al eliminar productos " + e.getMessage());
		}
	}
	
	public List<ProductoEntity> findByKeyWord(String palabraClave) throws Exception{
		List<ProductoEntity> productos = new ArrayList<ProductoEntity>(); 
		try {
			productos = this.repo.findByKeyWord(palabraClave);
			log.info("Se obtuvo la busqueda");
			return productos;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Erro al obtener la busqueda");
			throw new Exception("Error al obtener busqueda "+e.getMessage());
		}
	}
	
	public Long countPages() throws Exception {
		Long count;
		try {
			count = this.repo.count();
			log.info("cantidad de productos: "+count);
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("No se pudo obtener la cantidad todal de productos");
			throw new Exception("No se pudo obtener la cantidad todal de productos"+e.getMessage());
		}
	}
}
