package com.dac2014.json.model;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Transporte {

    private String nombre;

    private String descripcion;

    private double costo;
    
    private int cantidad;
    
    @ManyToOne
    private Disponibilidad disponibilidad;
}
