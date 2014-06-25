package com.dac2014.json.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Hotel {
	
	@Min(3)
	@NotNull
    private String nombre;

    private String descripcion;

    private double costo;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Imagen> imagenes = new HashSet<Imagen>();
}
