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
import com.caleb.cotizacioncreditos.entity.ProductoEntity;
import com.caleb.cotizacioncreditos.services.ProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping(value = "hola")
	public ResponseEntity<String> saludo() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}
	
	@GetMapping(value = "productos")
	public ResponseEntity<Body<List<ProductoEntity>>> listar(){
		List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
		Body<List<ProductoEntity>> body;
		try {
			productos = this.service.getProductos();
			body = new Body<List<ProductoEntity>>(200,"Successful",productos);
			return new ResponseEntity<Body<List<ProductoEntity>>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			body = new Body<List<ProductoEntity>>(500,"Error al obtener productos",null);
			return new ResponseEntity<Body<List<ProductoEntity>>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "productos-pagination")
	public ResponseEntity<Body<List<ProductoEntity>>> paginacion(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "nombre") String sortBy
			){
		List<ProductoEntity> productos;
		Body<List<ProductoEntity>> body;
		try {
			productos = this.service.getProductosP(pageNo, pageSize, sortBy);
			body = new Body<List<ProductoEntity>>(200,"Successful",productos);
			return new ResponseEntity<Body<List<ProductoEntity>>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body = new Body<List<ProductoEntity>>(500,"Error",null);
			return new ResponseEntity<Body<List<ProductoEntity>>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "add/producto")
	public ResponseEntity<Body<String>> agregar(@RequestBody ProductoEntity product) {
		Body<String> body;
		try {
			this.service.addProduct(product);
			if(product.getSku()==null || product.getSku().isEmpty()) {
				body = new Body<String>(200, "Se agrego nuevo producto", null);
			}else {
				body = new Body<String>(200, "Se actualizo el producto", null);
			}
			return new ResponseEntity<Body<String>>(body,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			body = new Body<String>(500, "No se pudo guardar o actualizar el producto", null);
			return new ResponseEntity<Body<String>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="producto/{id}")
	public ResponseEntity<Body<ProductoEntity>> gtProducto(
			@PathVariable(name = "id") String id){
		Body<ProductoEntity> body;
		ProductoEntity producto;
		try {
			producto = this.service.findById(id);
			body = new Body<ProductoEntity>(200,"Successful",producto);
			return new ResponseEntity<Body<ProductoEntity>>(body,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body = new Body<ProductoEntity>(500, "Error al obtener el producto por id ", null);
			return new ResponseEntity<Body<ProductoEntity>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "delete/producto/{id}")
	public ResponseEntity<Body<String>> eliminar(@PathVariable(name = "id") String id){
		Body<String> body;
		try {
			this.service.deleteProduct(id);
			body = new Body<String>(200, "Se elimino registro", null);
			return new ResponseEntity<Body<String>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			body = new Body<String>(500, "No se pudo eliminar el registro", null);
			return new ResponseEntity<Body<String>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "search/producto/{keyWord}")
	public ResponseEntity<Body<List<ProductoEntity>>> buscar(
			@PathVariable(name = "keyWord") String palabra,
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "sku") String sortBy
            ){
		Body<List<ProductoEntity>> body;
		List<ProductoEntity> productos = new ArrayList<ProductoEntity>();
		try {
			productos = this.service.findByKeyWord(palabra);
			body = new Body<List<ProductoEntity>>(200, "Successful", productos);
			return new ResponseEntity<Body<List<ProductoEntity>>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body = new Body<List<ProductoEntity>>(500, "Erro al obtener la busqueda", null);
			return new ResponseEntity<Body<List<ProductoEntity>>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "productos-count")
	public ResponseEntity<Body<Long>> countProductos(){
		Body<Long> body;
		try {
			Long count = this.service.countPages();
			body = new Body<Long>(200, "Successful", count);
			return new ResponseEntity<Body<Long>>(body, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			body = new Body<Long>(500, "Erro al obtener la cantidad de productos ", null);
			return new ResponseEntity<Body<Long>>(body, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
