// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.model;

import com.dac2014.json.model.Disponibilidad;
import com.dac2014.json.model.Hotel;
import com.dac2014.json.model.Imagen;
import java.util.Set;

privileged aspect Hotel_Roo_JavaBean {
    
    public String Hotel.getNombre() {
        return this.nombre;
    }
    
    public void Hotel.setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String Hotel.getDescripcion() {
        return this.descripcion;
    }
    
    public void Hotel.setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int Hotel.getCantidad() {
        return this.cantidad;
    }
    
    public void Hotel.setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double Hotel.getCosto() {
        return this.costo;
    }
    
    public void Hotel.setCosto(double costo) {
        this.costo = costo;
    }
    
    public Disponibilidad Hotel.getDisponibilidad() {
        return this.disponibilidad;
    }
    
    public void Hotel.setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    public Set<Imagen> Hotel.getImagenes() {
        return this.imagenes;
    }
    
    public void Hotel.setImagenes(Set<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
    
}
