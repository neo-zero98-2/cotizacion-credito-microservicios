package com.caleb.cotizacioncreditos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Body<T> {

	private Integer status;
	private String mensaje;
	private T body;
	
	
}
