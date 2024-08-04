package team_project.clat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j

public class apiExampleController {

    @PostMapping("/users/join")
    public String example(){
        return "ok";
    }
}
