package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.FiltroModalidad;
import com.empresa.entity.Modalidad;

public interface ModalidadRepository extends JpaRepository<Modalidad, Integer>  {
	
	@Query("select m from Modalidad m where ( :#{#filmd.nombre} is '' or m.nombre like :#{#filmd.nombre} ) and"
			+ "( :#{#filmd.idDeporte} is 0  or m.deporte.idDeporte = :#{#filmd.idDeporte} ) ")
	public abstract List<Modalidad> listarFiltroModalidad(@Param("filmd") FiltroModalidad filtro);
}
