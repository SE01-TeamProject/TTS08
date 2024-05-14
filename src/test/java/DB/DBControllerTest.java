package DB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DBControllerTest {
    @Autowired
    public testRepo testRepo;

    @Test
    void test() {
        testEntity t1=new testEntity();
        t1.setId(1L);
        t1.setName("test");
        testRepo.save(t1);
    }
}