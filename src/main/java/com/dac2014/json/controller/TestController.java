package com.dac2014.json.controller;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.dac2014.json.model.Actividad;
import com.dac2014.json.model.Disponibilidad;
import com.dac2014.json.model.Hotel;
import com.dac2014.json.model.Imagen;
import com.dac2014.json.model.Transporte;
import com.dac2014.json.model.Viaje;

@RequestMapping("/test")
@Controller
public class TestController {
	Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	Calendar c = Calendar.getInstance();
    
    @RequestMapping(value = "/{amount}", produces = "text/html")
    public String add(@PathVariable("amount") Long amount) {
    	
    	addImagenes();
    	
    	log.log(Level.INFO, "Recived");
		long lastId = Viaje.countViajes();
		
    	for(int i = 0; i <amount; i++){
    		long l = lastId + i;
    		Viaje v = new Viaje();
    		v.setActive(false);
    		v.setDescripcion("viaje_desc_"+l);
    		int pais = (int)(Math.random()*200);
    		
    		v.setDestino(pais);
    		v.setLocalidad("localidad_"+l);
    		
    		v.setFechaVencimiento(getRandomDate(30));
    		v.setNombre("nombre_"+l);
    		
    		v.setFeatured(false);
    		
    		pais = (int)(5+Math.random()*5);
    		v.setDisponibilidades(getDisponibilidades(pais, v));
    		
    		pais = (int)(5+Math.random()*5);
    		v.setActividades(getActividades(pais, v));
    		
    		v.setPrecio(1000+Math.random()*1000);
    		
    		v.setImagenes(getImagenes(v));
    		
    		v.persist();
    		
    		
    		
    	}
    	
    	
    	return "index";
    }
    
    private void addImagenes(){
    	Imagen im = new Imagen();
    	im.setDescripcion("viaje 1");
    	im.setNombre("nombre viaje 1");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\v1.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("viaje 2");
    	im.setNombre("nombre viaje 2");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\v2.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("viaje 3");
    	im.setNombre("nombre viaje 3");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\v3.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("viaje 4");
    	im.setNombre("nombre viaje 4");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\v4.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("hotel 1");
    	im.setNombre("nombre hotel 1");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\h1.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("hotel 2");
    	im.setNombre("nombre hotel 2");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\h2.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("hotel 3");
    	im.setNombre("nombre hotel 3");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\h3.jpg");
    	im.persist();
    	
    	im = new Imagen();
    	im.setDescripcion("actividad 1");
    	im.setNombre("nombre actividad 1");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\a1.jpg");
    	im.persist();
    	im = new Imagen();
    	im.setDescripcion("actividad 2");
    	im.setNombre("nombre actividad 2");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\a2.jpg");
    	im.persist();
    	im = new Imagen();
    	im.setDescripcion("actividad 3");
    	im.setNombre("nombre actividad 3");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\a3.jpg");
    	im.persist();
    	im = new Imagen();
    	im.setDescripcion("actividad 4");
    	im.setNombre("nombre actividad 4");
    	im.setContentType("image/jpeg");
    	im.setRuta("C:\\sts-bundle\\sts-3.6.0.M1\\a4.jpg");
    	im.persist();
    	
    }
    private Set<Actividad> getActividades(int j, Viaje v) {
    	long lastId = Actividad.countActividads();
    	Set<Actividad> ret = new HashSet<Actividad>();
    	
    	for(int i = 0; i <j; i++){
    		
    		long l = lastId + i;
    		Actividad t = new Actividad();
    		int c = (int)(20+Math.random()*80);
    		t.setFin(getRandomDate(10));
    		t.setInicio(getRandomDate(10));
    		t.setName("actividad_"+l);
    		t.setDescription("desc_act_"+l);
    		t.setViaje(v);
    		t.setImagenes(getImagenes(t));
    		ret.add(t);
    	}
    	return ret;
	}
	public Date getRandomDate(int i){
    	int offset = (int)(Math.random()*i);
    	c.setTime(new Date());
		c.add(Calendar.DATE, offset);
		return c.getTime();
    }
	public Date getRandomDate(int i, int ii){
    	int offset = (int)(ii+ Math.random()*i);
    	c.setTime(new Date());
		c.add(Calendar.DATE, offset);
		return c.getTime();
    }
    
    public Set<Disponibilidad> getDisponibilidades(int j, Viaje v){
    	long lastId = Disponibilidad.countDisponibilidads();
    	Set<Disponibilidad> ret = new HashSet<Disponibilidad>();
    	for(int i = 0; i <j; i++){
    		
    		long l = lastId + i;
    		Disponibilidad d = new Disponibilidad();
    		d.setFechaSalida(getRandomDate(100));
    		d.setViaje(v);
    		int x = (int)(2+Math.random()*5);
    		d.setTransportes(getTransportes(x, d));
    		d.setHoteles(getHoteles(x, d));
    		ret.add(d);
    	}
    	return ret;
    }
	private Set<Hotel> getHoteles(int j, Disponibilidad d) {
		long lastId = Hotel.countHotels();
    	Set<Hotel> ret = new HashSet<Hotel>();
    	
    	for(int i = 0; i <j; i++){
    		
    		long l = lastId + i;
    		Hotel h = new Hotel();
    		int c = (int)(20+Math.random()*80);
    		h.setCantidad(c);
    		h.setCosto(Math.random()*100);
    		h.setNombre("hotel_"+l);
    		h.setDescripcion("desc_hotel_"+l);
    		h.setDisponibilidad(d);
    		h.setImagenes(getImagenes(h));
    		ret.add(h);
    	}
    	return ret;
	}
	private Set<Transporte> getTransportes(int j, Disponibilidad d) {
		long lastId = Transporte.countTransportes();
    	Set<Transporte> ret = new HashSet<Transporte>();
    	
    	for(int i = 0; i <j; i++){
    		
    		long l = lastId + i;
    		Transporte t = new Transporte();
    		int c = (int)(20+Math.random()*80);
    		t.setCantidad(c);
    		t.setDescripcion("desc_trans_"+1);
    		t.setDisponibilidad(d);
    		t.setCosto(Math.random()*100);
    		t.setNombre("nombre_trans_"+l);
    		ret.add(t);
    	}
    	return ret;
	}
	
	private Set<Imagen> getImagenes(Viaje v){
    	Set<Imagen> ret = new HashSet<Imagen>();
    	
    	for(int i = 0; i <4; i++){
    		int j = 1+(int)(Math.random()*4);
    		log.log(Level.INFO, "viaje: "+j );
    		Imagen im = Imagen.findImagen((long)j);
    		im.getViajes().add(v);
    		ret.add(im);
    	}
    	return ret;
	}
	private Set<Imagen> getImagenes(Actividad v){
    	Set<Imagen> ret = new HashSet<Imagen>();
    	
    	for(int i = 0; i <4; i++){
    		int j = 8+(int)(Math.random()*4);
    		log.log(Level.INFO, "Actividad: "+j );
    		Imagen im = Imagen.findImagen((long)j);
    		im.getActividades().add(v);
    		ret.add(im);
    	}
    	return ret;
	}
	private Set<Imagen> getImagenes(Hotel v){
    	Set<Imagen> ret = new HashSet<Imagen>();
    	
    	for(int i = 0; i <4; i++){
    		int j = (int)(5+Math.random()*3);
        	log.log(Level.INFO, "Hotel: "+j );
    		Imagen im = Imagen.findImagen((long)j);
    		im.getHoteles().add(v);
    		ret.add(im);
    	}
    	return ret;
	}
}
