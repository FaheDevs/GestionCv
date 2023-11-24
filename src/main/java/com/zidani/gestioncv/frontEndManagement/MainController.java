package com.zidani.gestioncv.frontEndManagement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class MainController {

    @RequestMapping(value = {"/", "/login", "/dashboard"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

}
