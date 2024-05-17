package DB;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DBController {

    @GetMapping("/DB")
    @ResponseBody
    public String testdb(){
        return "Hello World";
    }
}
