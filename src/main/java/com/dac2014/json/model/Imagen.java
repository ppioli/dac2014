package com.dac2014.json.model;
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
    
    
    
}
