//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package backend.controller;

import com.spring.backend.service.WeeklyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WeeklyControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private WeeklyService weeklyService;

    WeeklyControllerTest() {
    }

    @Test
    void getAllWeekly() {
    }

    @Test
    void getWeeklyById() {
    }

    @Test
    void createNewWeekly() {
    }

    @Test
    void deleteWeekly() {
    }
}
