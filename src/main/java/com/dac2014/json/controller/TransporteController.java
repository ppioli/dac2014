package com.dac2014.json.controller;
import com.dac2014.json.model.Transporte;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/transportes")
@Controller
@RooWebScaffold(path = "transportes", formBackingObject = Transporte.class)
@RooWebJson(jsonObject = Transporte.class)
public class TransporteController {
}
