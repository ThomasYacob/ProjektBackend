//
//package backend.controller;
//
//import com.spring.backend.model.Daily;
//import com.spring.backend.service.DailyService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.sql.Date;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class DailyControllerTest {
//    @Autowired
//    private MockMvc mvc;
//    @MockBean
//    private DailyService dailyService;
//
//    DailyControllerTest() {
//    }
//
//    @Test
//    void getDaily() {
//        new Daily(1, "Vart ligger stockholm", "I Sverige", "I Norge", "I Finland", 1, new Date(1990L));
//        this.dailyService.getDaily(1);
//    }
//
//    @Test
//    void getDailyById() {
//    }
//
//    @Test
//    void createNewDaily() {
//        Daily temp = new Daily(1, "Vart ligger stockholm", "I Sverige", "I Norge", "I Finland", 1, new Date(1990L));
//        this.dailyService.createNewDaily(temp);
//        Assertions.assertEquals(1, temp.getRightAlternative());
//    }
//
//    @Test
//    void DeleteDaily() {
//        Daily temp = new Daily(1, "Vart ligger stockholm", "I Sverige", "I Norge", "I Finland", 1, new Date(1990L));
//        this.dailyService.createNewDaily(temp);
//        this.dailyService.deleteDaily(1);
//    }
//}
