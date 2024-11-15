package com.mx.empleadoBacken.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Empleados")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	
	@NotBlank(message = "El apellido paterno es obligatorio")
	private String apellidoPaterno;
	
	@NotBlank(message = "El nombre materno es obligatorio")
	private String apellidoMaterno;
	
	@NotBlank(message = "El curp es obligatorio")
	@Pattern(regexp = "^[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{2}[BCDFGHJKLMNPQRSTVWXYZ]{3}([A-Z0-9]{2})$", message = "La CURP no es válida")
	private String curp;
	
	@NotBlank(message = "El nombre es obligatorio")
	private String telefono;
}
