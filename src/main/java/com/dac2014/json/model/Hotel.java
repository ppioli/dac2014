package com.dac2014.json.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Hotel {
	
	private String nombre;

    private String descripcion;
    
    private int cantidad;
    
    //faltan habitaciones

    private double costo;
    
    @ManyToOne
    private Disponibilidad disponibilidad;
    
    /**
     */
    
    @ManyToMany
    @JoinTable(name="HOT_IMG")
    private Set<Imagen> imagenes = new HashSet<Imagen>();
}
