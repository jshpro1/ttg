/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deu.se.ttg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jshpr
 */
@Controller
public class choicecontroller {
   
    @GetMapping("/choice")
    public String choice() {
        return "choice/index";
    }
    
    @GetMapping("/choice/game/yatch")
    public String yatchdice() {
        return "choice/game/yatch/index";
    }
    
}
