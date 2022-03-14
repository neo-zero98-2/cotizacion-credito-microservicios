package com.caleb.cotizacioncreditos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "plazo")
public class PlazoEntity {

	@Id
	@Column(name = "idplazos")
	private String idplazos;
	
	@Column(name = "plazo")
	private Integer plazo;
	
	@Column(name = "taza_normal")
	private Double tazaNormal;
	
	@Column(name = "taza_puntual")
	private Double tazaPuntual;
}
