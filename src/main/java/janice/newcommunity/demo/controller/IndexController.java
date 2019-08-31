package janice.newcommunity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Janice on 2019-08-30 20:59
 */
@Controller
public class IndexController{
    @GetMapping("/")
    public String index(){
        return "index";
    }
}

