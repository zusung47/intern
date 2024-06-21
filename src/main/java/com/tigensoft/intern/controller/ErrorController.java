package com.tigensoft.intern.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j
public class ErrorController {

    @RequestMapping(value = "/login/error", method = RequestMethod.GET)
    public String loginError(@RequestParam(value = "error", required = false)String error,
                             @RequestParam(value = "exception", required = false)String exception, Model model){

        log.warn("==============ErrorController=================");
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "views/login-form";
    }
}
