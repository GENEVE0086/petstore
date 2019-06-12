package org.csu.stnb.petstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/viewOrderForm")
    public String viewOrderForm(){
        return "order/newOrderForm";
    }
}
