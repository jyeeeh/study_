package co.socket.socketpro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String vue() {
        return "vue/index";  // templates/vue/index.html을 반환
    }
}