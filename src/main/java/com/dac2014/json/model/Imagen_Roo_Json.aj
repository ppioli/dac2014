// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.model;

import com.dac2014.json.model.Imagen;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Imagen_Roo_Json {
    
    public String Imagen.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Imagen.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Imagen Imagen.fromJsonToImagen(String json) {
        return new JSONDeserializer<Imagen>()
        .use(null, Imagen.class).deserialize(json);
    }
    
    public static String Imagen.toJsonArray(Collection<Imagen> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Imagen.toJsonArray(Collection<Imagen> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Imagen> Imagen.fromJsonArrayToImagens(String json) {
        return new JSONDeserializer<List<Imagen>>()
        .use("values", Imagen.class).deserialize(json);
    }
    
}