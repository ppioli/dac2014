// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.model;

import com.dac2014.json.model.Disponibilidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Disponibilidad_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Disponibilidad.entityManager;
    
    public static final List<String> Disponibilidad.fieldNames4OrderClauseFilter = java.util.Arrays.asList("hoteles", "transportes", "viaje", "fechaSalida");
    
    public static final EntityManager Disponibilidad.entityManager() {
        EntityManager em = new Disponibilidad().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Disponibilidad.countDisponibilidads() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Disponibilidad o", Long.class).getSingleResult();
    }
    
    public static List<Disponibilidad> Disponibilidad.findAllDisponibilidads() {
        return entityManager().createQuery("SELECT o FROM Disponibilidad o", Disponibilidad.class).getResultList();
    }
    
    public static List<Disponibilidad> Disponibilidad.findAllDisponibilidads(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Disponibilidad o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Disponibilidad.class).getResultList();
    }
    
    public static Disponibilidad Disponibilidad.findDisponibilidad(Long id) {
        if (id == null) return null;
        return entityManager().find(Disponibilidad.class, id);
    }
    
    public static List<Disponibilidad> Disponibilidad.findDisponibilidadEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Disponibilidad o", Disponibilidad.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Disponibilidad> Disponibilidad.findDisponibilidadEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Disponibilidad o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Disponibilidad.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Disponibilidad.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Disponibilidad.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Disponibilidad attached = Disponibilidad.findDisponibilidad(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Disponibilidad.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Disponibilidad.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Disponibilidad Disponibilidad.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Disponibilidad merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
