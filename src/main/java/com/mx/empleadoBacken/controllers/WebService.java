package com.mx.empleadoBacken.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.empleadoBacken.entitites.Empleado;
import com.mx.empleadoBacken.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:4200/")
public class WebService {

	@Autowired
	private EmpleadoService service;

	@GetMapping("/list-empleado")
	public ResponseEntity<?> list() {
		List<Empleado> list = this.service.list();
		Map<String, Object> response = new HashMap<>();
		if (list.size() > 0) {
			return ResponseEntity.ok(list);
		} else {
			response.put("message", "No hay datos");
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/empleado/{id}")
	public ResponseEntity<?> getEmpleadoById(@PathVariable Long id) {
		Empleado empleado = this.service.getEmpleadoById(id);
		Map<String, Object> response = new HashMap<>();
		if (empleado != null) {
			response.put("message", "Empleado con ID " + id + " encontrado"); 
			response.put("empleado", empleado);
			return ResponseEntity.ok(response);
		} else {
			response.put("message", "Empleado con ID " + id + " no encontrado"); 
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/empleado")
	public ResponseEntity<?> saveEmpleado(@RequestBody @Valid Empleado empleadoBody) {
		Empleado empleado = this.service.saveEmpleado(empleadoBody);
		Map<String, Object> response = new HashMap<>();

		response.put("message", "Se registro datos empleado");
		response.put("empleado", empleado);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/empleado/{id}")
	public ResponseEntity<?> updateEmpleadoById(@PathVariable Long id,@RequestBody @Valid Empleado empleadoBody) {
		Empleado empleado = this.service.getEmpleadoById(id);
		Map<String, Object> response = new HashMap<>();
		if (empleado != null) {
			
			empleado.setNombre(empleadoBody.getNombre());
			empleado.setApellidoPaterno(empleadoBody.getApellidoPaterno());
			empleado.setApellidoMaterno(empleadoBody.getApellidoMaterno());
			empleado.setCurp(empleadoBody.getCurp());
			empleado.setTelefono(empleadoBody.getTelefono());
			Empleado empleadoUpdate = this.service.saveEmpleado(empleado);
			response.put("message", "Empleado actualizado correctamente");
			response.put("empleado", empleadoUpdate);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put("message", "Empleado con ID " + id + " no encontrado"); 
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> deleteEmpleadoById(@PathVariable Long id) {
	    Empleado empleado = this.service.getEmpleadoById(id);
	    Map<String, Object> response = new HashMap<>();
	    if (empleado != null) {
	        this.service.deleteEmpleado(empleado);
	        response.put("message", "Empleado eliminado correctamente");
	        response.put("empleado", empleado);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        response.put("message", "Empleado con ID " + id + " no encontrado");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	}


}
