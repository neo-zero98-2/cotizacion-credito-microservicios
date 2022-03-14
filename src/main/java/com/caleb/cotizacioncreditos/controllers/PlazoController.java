package com.caleb.cotizacioncreditos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caleb.cotizacioncreditos.dto.Body;
import com.caleb.cotizacioncreditos.entity.PlazoEntity;
import com.caleb.cotizacioncreditos.services.PlazoService;

@RestController
public class PlazoController {

	@Autowired
	private PlazoService service;
	
	@GetMapping(value = "plazos")
	public ResponseEntity<Body<List<PlazoEntity>>> listar(){
		List<PlazoEntity> plazos = new ArrayList<PlazoEntity>();
		Body<List<PlazoEntity>> body;
		try {
			plazos = this.service.getPlazos();
			body = new Body<List<PlazoEntity>>(200,"Successful",plazos);
			return new ResponseEntity<Body<List<PlazoEntity>>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			body = new Body<List<PlazoEntity>>(500,"Error",null);
			return new ResponseEntity<Body<List<PlazoEntity>>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "plazos-pagination")
	public ResponseEntity<Body<List<PlazoEntity>>> paginacion(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "plazo") String sortBy
			){
		List<PlazoEntity> productos;
		Body<List<PlazoEntity>> body;
		try {
			productos = this.service.getPlazosP(pageNo, pageSize, sortBy);
			body = new Body<List<PlazoEntity>>(200,"Successful",productos);
			return new ResponseEntity<Body<List<PlazoEntity>>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body = new Body<List<PlazoEntity>>(500,"Error",null);
			return new ResponseEntity<Body<List<PlazoEntity>>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "add/plazo")
	public ResponseEntity<Body<String>> agregar(@RequestBody PlazoEntity plazo) {
		Body<String> body;
		try {
			this.service.addPlazo(plazo);
			if(plazo.getIdplazos()==null || plazo.getIdplazos().isEmpty()) {
				body = new Body<String>(200, "Successful", "Se agrego nuevo producto");
			}else {
				body = new Body<String>(200, "Successful", "Se actualizo el producto");
			}
			return new ResponseEntity<Body<String>>(body,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			body = new Body<String>(500, "No se pudo guardar o actualizar el producto", null);
			return new ResponseEntity<Body<String>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "delete/plazo/{id}")
	public ResponseEntity<Body<String>> eliminar(@PathVariable(name = "id") String id){
		Body<String> body;
		try {
			this.service.deletePlazo(id);
			body = new Body<String>(200, "Successful", "Se elimino registro");
			return new ResponseEntity<Body<String>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			body = new Body<String>(500, "No se pudo eliminar el registro", null);
			return new ResponseEntity<Body<String>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "plazos-count")
	public ResponseEntity<Body<Long>> countPlazos(){
		Body<Long> body;
		try {
			Long count = this.service.countPages();
			body = new Body<Long>(200, "Successful", count);
			return new ResponseEntity<Body<Long>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body = new Body<Long>(500, "Erro al obtener la cantidad de plazos", null);
			return new ResponseEntity<Body<Long>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
