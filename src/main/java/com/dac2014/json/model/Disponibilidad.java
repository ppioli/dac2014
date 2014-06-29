package com.dac2014.json.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Disponibilidad {

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy="disponibilidad")
    private Set<Hotel> hoteles = new HashSet<Hotel>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy="disponibilidad")
    private Set<Transporte> transportes = new HashSet<Transporte>();
    
    @ManyToOne
	private Viaje viaje;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaSalida;
}
