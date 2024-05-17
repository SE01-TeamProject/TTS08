package DB;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DBController.class)
public class test_DBTest {

    @Autowired
    private testRepo testRepo;

    @Test
    public void test() {
        testEntity t1 = new testEntity();
        t1.setId(1L);
        t1.setName("test");
        testRepo.save(t1);
    }
}