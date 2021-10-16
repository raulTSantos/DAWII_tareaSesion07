package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Docente;
import com.empresa.entity.FiltroDocente;
import com.empresa.service.DocenteService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/docente")
//@CrossOrigin(origins = "http://localhost:4200")
public class DocenteController {

	@Autowired
	private DocenteService docenteService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocente() {
		List<Docente> lista = docenteService.listaDocente();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Docente obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Docente objSalida = docenteService.insertaActualizaDocente(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	
	@GetMapping("/porDni/{paramDni}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorDni(@PathVariable("paramDni")String dni) {
		List<Docente> lista = docenteService.listaDocentePorDni(dni);
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/porNombre/{paramNombre}")
	@ResponseBody
	public ResponseEntity<List<Docente>> listaDocentePorNombre(@PathVariable("paramNombre")String nombre) {
		List<Docente> lista = docenteService.listaDocentePorNombre(nombre);
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/porDniNombre/{paramDni}/{paramNombre}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocentePorDniNombre(
										@PathVariable("paramDni")String dni,
										@PathVariable("paramNombre")String nombre) {
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService .listaDocentePorDniNombre(dni, "%"+nombre+"%");
			if(CollectionUtils.isEmpty(lista)){
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping("/porDniNombreConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaPrDniNombreConParametros(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "dni", required = false, defaultValue = "") String dni) {
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService .listaDocentePorDniNombre(dni, "%"+nombre+"%");
			if(CollectionUtils.isEmpty(lista)){
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/porDniNombreUbigeoConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaPrDniNombreUbigeoConParametros(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "dni", required = false, defaultValue = "") String dni,
			@RequestParam(value = "idUbigeo", required = false, defaultValue = "0") int idUbigeo) {
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Docente> lista = docenteService.listaDocentePorDniNombreUbigeo(dni, "%"+nombre+"%", idUbigeo);
			if(CollectionUtils.isEmpty(lista)){
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/porDniNombreUbigeoConJson")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaPrDniNombreUbigeoConParametros(
							@RequestBody FiltroDocente filtro) {
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			filtro.setNombre("%"+filtro.getNombre()+"%");
			List<Docente> lista = docenteService.listaPorFiltro(filtro);
			if(CollectionUtils.isEmpty(lista)){
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
}







