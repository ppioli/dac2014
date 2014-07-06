package com.dac2014.json.controller;

import java.util.logging.Level;

import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dac2014.json.model.Disponibilidad;
import com.dac2014.json.model.Hotel;
import com.dac2014.json.model.Transporte;
import com.dac2014.json.model.Usuario;
import com.dac2014.json.model.Viaje;


@RequestMapping("/home")
@Controller
public class HomeController {
	
	
	@RequestMapping(value = "/{id}", produces = "text/html")
	public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Viaje v = Viaje.findViaje(id);
        uiModel.addAttribute("viaje", v);
        //uiModel.addAttribute("imagenes", v.getImagenes());
        System.out.println(v.getDisponibilidades().size());
        //uiModel.addAttribute("disponibilidades", v.getDisponibilidades());
        //uiModel.addAttribute("actividades", v.getActividades());
        return "home/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("viajes", Viaje.findViajeEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Viaje.countViajes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("viajes", Viaje.findAllViajes(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "home/list";
    }
    
    @RequestMapping(value="buy", produces = "text/html")
    public String comprar(@RequestParam(value = "fecha", required = true) Long disp_id, 
    		@RequestParam(value = "viaje", required = true) Long viaje_id,
    		@RequestParam(value = "transporte", required = true) Long transporte_id,
    		@RequestParam(value = "hotel", required = true) Long hotel_id, Model uiModel){
    		
    	uiModel.addAttribute("viaje", Viaje.findViaje(viaje_id));
    	uiModel.addAttribute("transporte", Transporte.findTransporte(transporte_id));
    	uiModel.addAttribute("hotel", Hotel.findHotel(hotel_id));
    	uiModel.addAttribute("disponibilidad", Disponibilidad.findDisponibilidad(disp_id));
        return "home/buy";
    }
    
    @RequestMapping(value = "abm", produces = "text/html")
    public String pruba(){
        
        return "index2";
    }
    
    @RequestMapping(value = "/addusers", produces = "text/html")
    public String addUsers(){
        Usuario u = new Usuario();
        
        u.setNombre("Juan");
        u.setEmail("email@email.com");
        u.setEnable(true);
        u.setPassword("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
        u.setUsername("admin");
        u.setRole("ROLE_ADMIN");
        u.persist();
        
        return "index2";
    }
    
    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("viaje_fechavencimiento_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
}
