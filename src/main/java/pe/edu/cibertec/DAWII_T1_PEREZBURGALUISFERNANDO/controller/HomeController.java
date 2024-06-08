package pe.edu.cibertec.DAWII_T1_PEREZBURGALUISFERNANDO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
