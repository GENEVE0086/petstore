package org.csu.stnb.petstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewHelpController {

    @GetMapping("/viewHelp")
    public String viewHelp() {
        return "help";
    }
}
