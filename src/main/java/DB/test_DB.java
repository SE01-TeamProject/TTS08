package DB;

import jakarta.annotation.security.RunAs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class test_DB {

    public static void main(String[] args) {
        SpringApplication.run(test_DB.class,args);

    }
}
