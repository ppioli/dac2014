// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.controller;

import com.dac2014.json.controller.DisponibilidadController;
import com.dac2014.json.model.Disponibilidad;
import com.dac2014.json.model.Hotel;
import com.dac2014.json.model.Transporte;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect DisponibilidadController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String DisponibilidadController.create(@Valid Disponibilidad disponibilidad, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, disponibilidad);
            return "disponibilidads/create";
        }
        uiModel.asMap().clear();
        disponibilidad.persist();
        return "redirect:/disponibilidads/" + encodeUrlPathSegment(disponibilidad.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String DisponibilidadController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Disponibilidad());
        return "disponibilidads/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String DisponibilidadController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("disponibilidad", Disponibilidad.findDisponibilidad(id));
        uiModel.addAttribute("itemId", id);
        return "disponibilidads/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String DisponibilidadController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("disponibilidads", Disponibilidad.findDisponibilidadEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Disponibilidad.countDisponibilidads() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("disponibilidads", Disponibilidad.findAllDisponibilidads(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "disponibilidads/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String DisponibilidadController.update(@Valid Disponibilidad disponibilidad, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, disponibilidad);
            return "disponibilidads/update";
        }
        uiModel.asMap().clear();
        disponibilidad.merge();
        return "redirect:/disponibilidads/" + encodeUrlPathSegment(disponibilidad.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String DisponibilidadController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Disponibilidad.findDisponibilidad(id));
        return "disponibilidads/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String DisponibilidadController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Disponibilidad disponibilidad = Disponibilidad.findDisponibilidad(id);
        disponibilidad.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/disponibilidads";
    }
    
    void DisponibilidadController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("disponibilidad_fechasalida_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void DisponibilidadController.populateEditForm(Model uiModel, Disponibilidad disponibilidad) {
        uiModel.addAttribute("disponibilidad", disponibilidad);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("hotels", Hotel.findAllHotels());
        uiModel.addAttribute("transportes", Transporte.findAllTransportes());
    }
    
    String DisponibilidadController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
