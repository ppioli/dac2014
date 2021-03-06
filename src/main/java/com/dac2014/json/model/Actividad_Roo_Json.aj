// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.model;

import com.dac2014.json.model.Actividad;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Actividad_Roo_Json {
    
    public String Actividad.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Actividad.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Actividad Actividad.fromJsonToActividad(String json) {
        return new JSONDeserializer<Actividad>()
        .use(null, Actividad.class).deserialize(json);
    }
    
    public static String Actividad.toJsonArray(Collection<Actividad> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Actividad.toJsonArray(Collection<Actividad> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Actividad> Actividad.fromJsonArrayToActividads(String json) {
        return new JSONDeserializer<List<Actividad>>()
        .use("values", Actividad.class).deserialize(json);
    }
    
}
