// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dac2014.json.controller;

import com.dac2014.json.controller.TransporteController;
import com.dac2014.json.model.Disponibilidad;
import com.dac2014.json.model.Transporte;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect TransporteController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String TransporteController.create(@Valid Transporte transporte, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, transporte);
            return "transportes/create";
        }
        uiModel.asMap().clear();
        transporte.persist();
        return "redirect:/transportes/" + encodeUrlPathSegment(transporte.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String TransporteController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Transporte());
        return "transportes/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String TransporteController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("transporte", Transporte.findTransporte(id));
        uiModel.addAttribute("itemId", id);
        return "transportes/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String TransporteController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("transportes", Transporte.findTransporteEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Transporte.countTransportes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("transportes", Transporte.findAllTransportes(sortFieldName, sortOrder));
        }
        return "transportes/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String TransporteController.update(@Valid Transporte transporte, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, transporte);
            return "transportes/update";
        }
        uiModel.asMap().clear();
        transporte.merge();
        return "redirect:/transportes/" + encodeUrlPathSegment(transporte.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String TransporteController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Transporte.findTransporte(id));
        return "transportes/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String TransporteController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Transporte transporte = Transporte.findTransporte(id);
        transporte.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/transportes";
    }
    
    void TransporteController.populateEditForm(Model uiModel, Transporte transporte) {
        uiModel.addAttribute("transporte", transporte);
        uiModel.addAttribute("disponibilidads", Disponibilidad.findAllDisponibilidads());
    }
    
    String TransporteController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
