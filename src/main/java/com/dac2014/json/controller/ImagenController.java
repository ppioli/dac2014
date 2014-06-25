package com.dac2014.json.controller;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.dac2014.json.model.Imagen;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/imagens")
@Controller
@RooWebScaffold(path = "imagens", formBackingObject = Imagen.class)
@RooWebJson(jsonObject = Imagen.class)
public class ImagenController {

    private static final String THUMB_SUFFIX = "-thumb";

    @RequestMapping(value = "/{id}/image", method = RequestMethod.GET)
    public String showImage(@PathVariable("id") Long id, HttpServletResponse response, Model model, @RequestParam(value = "thumb", required = false) boolean thumb) {
        Imagen imagen = Imagen.findImagen(id);
        if (imagen != null && imagen.getRuta() != null) {
            String ruta = imagen.getRuta();
            if (thumb) {
                String path = FilenameUtils.getFullPath(ruta);
                String ext = FilenameUtils.getExtension(ruta);
                String name = FilenameUtils.getBaseName(ruta);
                ruta = path + name + THUMB_SUFFIX + "." + ext;
            }
            try {
                OutputStream out = response.getOutputStream();
                response.setContentType(imagen.getContentType());
                Files.copy(FileSystems.getDefault().getPath(ruta), out);
                out.flush(); // Y esto te maneja el cache??? porque no dejas que el sv maneje todo????
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Imagen imagen, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, @RequestParam("image") MultipartFile multipartFile) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, imagen);
            return "imagens/create";
        }
        uiModel.asMap().clear();
        CommonsMultipartFile image = imagen.getImage();
        if (image != null) {
            File file = new File(image.getOriginalFilename());
            try {
                image.transferTo(file);
                imagen.setContentType(image.getContentType());
                String path = file.getAbsolutePath();
                imagen.setRuta(path);
                String suffix = FilenameUtils.getExtension(path);
                String rem = FilenameUtils.removeExtension(path);
                Thumbnails.of(file).size(120, 120).toFile(rem + THUMB_SUFFIX + "." + suffix);
            } catch (Exception e) {
                e.printStackTrace();
                return "imagens/create";
            }
        }
        imagen.persist();
        return "redirect:/imagens/" + encodeUrlPathSegment(imagen.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Imagen());
        return "imagens/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("imagen", Imagen.findImagen(id));
        uiModel.addAttribute("itemId", id);
        return "imagens/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("imagens", Imagen.findImagenEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Imagen.countImagens() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("imagens", Imagen.findAllImagens(sortFieldName, sortOrder));
        }
        return "imagens/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Imagen imagen, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, @RequestParam("image") MultipartFile multipartFile) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, imagen);
            return "imagens/update";
        }
        uiModel.asMap().clear();
        imagen.setContentType(multipartFile.getContentType());
        imagen.merge();
        return "redirect:/imagens/" + encodeUrlPathSegment(imagen.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Imagen.findImagen(id));
        return "imagens/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Imagen imagen = Imagen.findImagen(id);
        imagen.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/imagens";
    }

    void populateEditForm(Model uiModel, Imagen imagen) {
        uiModel.addAttribute("imagen", imagen);
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }
}
