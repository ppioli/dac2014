package com.dac2014.json.services;
import com.dac2014.json.model.Imagen;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Imagen.class)
@Controller
@RequestMapping("/json/imagens")
public class ImagenRESTController {
}
