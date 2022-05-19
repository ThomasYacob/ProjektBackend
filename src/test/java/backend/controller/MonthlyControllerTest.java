
package backend.controller;

import com.spring.backend.service.MonthlyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MonthlyControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private MonthlyService monthlyService;

    MonthlyControllerTest() {
    }

    @Test
    void getAllMonthly() {
    }

    @Test
    void getMonthlyById() {
    }

    @Test
    void createNewMonthly() {
    }

    @Test
    void deleteMonthly() {
    }
}
