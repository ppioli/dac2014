package com.dac2014.json.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Viaje {

    private String nombre;

    private String descripcion;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Actividad> actividades = new HashSet<Actividad>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Disponibilidad> disponibilidades = new HashSet<Disponibilidad>();

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaVencimiento;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Imagen> imagenes = new HashSet<Imagen>();

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static Viaje fromJsonToViaje(String json) {
        return new JSONDeserializer<Viaje>()
        .use(null, Viaje.class).deserialize(json);
    }

	public static String toJsonArray(Collection<Viaje> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<Viaje> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<Viaje> fromJsonArrayToViajes(String json) {
        return new JSONDeserializer<List<Viaje>>()
        .use("values", Viaje.class).deserialize(json);
    }
}
