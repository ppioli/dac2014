package com.dac2014.json.controller;
import com.dac2014.json.model.Disponibilidad;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/disponibilidads")
@Controller
@RooWebScaffold(path = "disponibilidads", formBackingObject = Disponibilidad.class)
@RooWebJson(jsonObject = Disponibilidad.class)
public class DisponibilidadController {
}
