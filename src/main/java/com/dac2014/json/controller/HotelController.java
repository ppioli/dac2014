package com.dac2014.json.controller;
import com.dac2014.json.model.Actividad;
import com.dac2014.json.model.Hotel;
import com.dac2014.json.model.Imagen;
import com.dac2014.json.model.Viaje;

import flexjson.JSONSerializer;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/hotels")
@Controller
@RooWebScaffold(path = "hotels", formBackingObject = Hotel.class)
@RooWebJson(jsonObject = Hotel.class)
public class HotelController {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        Hotel hotel = Hotel.findHotel(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (hotel == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(hotel.toJson(), headers, HttpStatus.OK);
    }

	@RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Hotel> result = Hotel.findAllHotels();
        return new ResponseEntity<String>(Hotel.toJsonArray(result), headers, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
    public ResponseEntity<String> createFromJson(@RequestBody String json, UriComponentsBuilder uriBuilder) {
		HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Hotel hotel;
		try{
        	hotel = Hotel.fromJsonToHotel(json);
        	hotel.persist();
        }catch(Exception e){
        	
        	return new ResponseEntity<String>(
        			"{\"message\":"+e.getLocalizedMessage()+"}",
        			headers, HttpStatus.BAD_REQUEST);
        }
        
        //headers.add("Content-Type", "application/json");
        RequestMapping a = (RequestMapping) getClass().getAnnotation(RequestMapping.class);
        headers.add("Location",uriBuilder.path(a.value()[0]+"/"+hotel.getId().toString()).build().toUriString());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (Hotel hotel: Hotel.fromJsonArrayToHotels(json)) {
            hotel.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
		System.out.println("Im in put");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Hotel hotel = Hotel.fromJsonToHotel(json);
        
        hotel.setId(id);
        System.out.println(hotel);
        Hotel as = null ;
        try {
        	as = hotel.merge(); 
		} catch (Exception e) {
			// TODO: handle exception
		}
        if (as == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        Hotel hotel = Hotel.findHotel(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (hotel == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        hotel.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/test")
    public ResponseEntity<String> test() {
		
        HttpHeaders headers = new HttpHeaders();
        Viaje v;
        v = Viaje.fromJsonToViaje("{\"actividades\":[{\"fin\":1402361431604,\"id\":1,\"inicio\":null,\"version\":0}],\"descripcion\":null,\"fechaVencimiento\":null,\"id\":1,\"nombre\":\"Un nuevo viaje\",\"version\":0}");
		try{
			v.merge();
			System.out.println(v.toJson(new String[]{"actividades"}));
		}catch(Exception e ){
			System.out.println(e.getLocalizedMessage());
		}        
        
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
