package jh.project.lookupbl.controller;

import jh.project.lookupbl.form.HblForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/test")
    public String test(@ModelAttribute HblForm hblForm) {
        return "test";
    }

    @PostMapping("/test")
    public String testPost(@ModelAttribute HblForm hblForm) {
        //여러개 입력 시 구분자(,) 로 구분돼서 담아짐
        System.out.println("---------------------> " + hblForm.getBlYy());
        System.out.println("---------------------> " + hblForm.getHblNo());


        return "test";
    }
}
