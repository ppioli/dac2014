// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.model;

import com.dac2014.json.model.Actividad;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Actividad_Roo_Jpa_Entity {
    
    declare @type: Actividad: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Actividad.id;
    
    @Version
    @Column(name = "version")
    private Integer Actividad.version;
    
    public Long Actividad.getId() {
        return this.id;
    }
    
    public void Actividad.setId(Long id) {
        this.id = id;
    }
    
    public Integer Actividad.getVersion() {
        return this.version;
    }
    
    public void Actividad.setVersion(Integer version) {
        this.version = version;
    }
    
}