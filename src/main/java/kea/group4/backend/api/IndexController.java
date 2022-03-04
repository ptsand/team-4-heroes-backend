package kea.group4.backend.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class IndexController {

    @GetMapping
    public String someMethod() {
        return "nnnnnn";
    }

}
