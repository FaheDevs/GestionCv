package com.zidani.gestioncv.frontEndManagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        // this attribute will be available in the view index.html as a thymeleaf variable
        model.addAttribute("eventName", "FIFA 2018");
        // this just means render index.html from static/ area
        return "index";
    }


}
