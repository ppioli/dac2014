package com.dac2014.json.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Imagen {

    /**
     */
    private String nombre;

    /**
     */
    private String descripcion;

    /**
     */
    private String ruta;
    /**
     * 
     */
    @Transient
    private CommonsMultipartFile image;
    /**
     * 
     */
    private String contentType;
    
    @ManyToMany(mappedBy="imagenes")
    private Set<Viaje> viajes;
    
    @ManyToMany(mappedBy="imagenes")
    private Set<Hotel> hoteles;
    
    @ManyToMany(mappedBy="imagenes")
    private Set<Actividad> actividades;
 }
