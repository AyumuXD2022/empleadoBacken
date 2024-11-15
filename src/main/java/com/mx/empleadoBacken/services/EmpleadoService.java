package com.mx.empleadoBacken.services;

import java.util.List;

import com.mx.empleadoBacken.entitites.Empleado;

public interface EmpleadoService {

	List<Empleado> list();

	Empleado getEmpleadoById(Long id);

	Empleado saveEmpleado(Empleado empleado);

	void deleteEmpleado(Empleado empleado);

}
