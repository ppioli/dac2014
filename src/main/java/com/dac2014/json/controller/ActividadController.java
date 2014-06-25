package com.dac2014.json.controller;
import com.dac2014.json.model.Actividad;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/actividads")
@Controller
@RooWebScaffold(path = "actividads", formBackingObject = Actividad.class)
@RooWebJson(jsonObject = Actividad.class)
public class ActividadController {
}
