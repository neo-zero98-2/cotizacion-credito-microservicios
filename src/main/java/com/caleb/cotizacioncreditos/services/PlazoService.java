package com.caleb.cotizacioncreditos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caleb.cotizacioncreditos.dao.PlazoDao;
import com.caleb.cotizacioncreditos.dao.PlazoPaginacionDao;
import com.caleb.cotizacioncreditos.entity.PlazoEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlazoService {

	@Autowired
	private PlazoDao repo;
	
	@Autowired
	private PlazoPaginacionDao repoPaginacion;
	
	public List<PlazoEntity> getPlazos() throws Exception{
		List<PlazoEntity> plazos = new ArrayList<PlazoEntity>();
		try {
			 plazos = this.repo.findAll();
			log.info("Se obtuvieron todos los plazos");
			return plazos;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al obtener los plazos");
			throw new Exception("Error al obtener los plazos "+e.getMessage());
		}
	}
	
	public List<PlazoEntity> getPlazosP(
			Integer pageNo, Integer pageSize, String sortBy
			) throws Exception{
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<PlazoEntity> pagedResult = this.repoPaginacion.findAll(paging);
			return pagedResult.getContent();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al obtener los plazos paginados");
			throw new Exception("Error al obtener los plazos paginados"+e.getMessage());
		}
		
	}
	
	public void addPlazo(PlazoEntity plazo) throws Exception {
		try {
			//log.info("idPlazo: "+plazo.getIdPlazos());
			this.repo.save(plazo);
			log.info("Se agrego el plazo");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al agregar el plazo ");
			throw new Exception("Error al agregar el plazo "+e.getMessage());
		}
	}
	
	public void deletePlazo(String idPlazo) throws Exception {
		try {
			this.repo.deleteById(idPlazo);
			log.info("Se elimino el plazo");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al eliminar el plazo");
			throw new Exception("Error al eliminar el plazo " + e.getMessage());
		}
	}
	
	public Long countPages() throws Exception {
		Long count;
		try {
			count = this.repo.count();
			log.info("cantidad de plazos: "+count);
			return count;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("No se pudo obtener la cantidad todal de plazos");
			throw new Exception("No se pudo obtener la cantidad todal de plazos"+e.getMessage());
		}
	}
}
