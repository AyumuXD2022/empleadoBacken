package com.mx.empleadoBacken.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.empleadoBacken.entitites.Empleado;
import com.mx.empleadoBacken.repositories.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository repository;
	
	@Override
	public List<Empleado> list(){
		return this.repository.findAll();
	}
	@Override
	public Empleado getEmpleadoById(Long id) {
		return this.repository.findById(id).orElse(null);
	}
	@Override
	public Empleado saveEmpleado(Empleado empleado) {
		return this.repository.save(empleado);
	}
	@Override
	public void deleteEmpleado(Empleado empleado) {
		this.repository.delete(empleado);
	}
}
