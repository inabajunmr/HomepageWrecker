package homepagewrecker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WreckerController {
    @RequestMapping("inputUrl")
    String loginForm() {
        return "wreck/inputUrl";
    }
}
