// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.model;

import com.dac2014.json.model.Imagen;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Imagen_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Imagen.entityManager;
    
    public static final List<String> Imagen.fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "descripcion", "ruta", "image", "contentType");
    
    public static final EntityManager Imagen.entityManager() {
        EntityManager em = new Imagen().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Imagen.countImagens() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Imagen o", Long.class).getSingleResult();
    }
    
    public static List<Imagen> Imagen.findAllImagens() {
        return entityManager().createQuery("SELECT o FROM Imagen o", Imagen.class).getResultList();
    }
    
    public static List<Imagen> Imagen.findAllImagens(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Imagen o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Imagen.class).getResultList();
    }
    
    public static Imagen Imagen.findImagen(Long id) {
        if (id == null) return null;
        return entityManager().find(Imagen.class, id);
    }
    
    public static List<Imagen> Imagen.findImagenEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Imagen o", Imagen.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Imagen> Imagen.findImagenEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Imagen o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Imagen.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Imagen.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Imagen.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Imagen attached = Imagen.findImagen(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Imagen.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Imagen.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Imagen Imagen.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Imagen merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}