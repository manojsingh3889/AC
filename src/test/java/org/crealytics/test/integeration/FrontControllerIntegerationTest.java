package org.crealytics.test.integeration;

import org.crealytics.BootStrap;
import org.crealytics.repository.AdRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")

public class FrontControllerIntegerationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AdRepository adRepositoryImple;

//    @Test
//    public void givenEmployees_whenGetEmployees_thenStatus200()
//            throws Exception {
//
//        createTestEmployee("bob");
//
//        mvc.perform(get("/api/employees")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].name", is("bob")));
//    }

    @Test
    public void test_insert_api() throws Exception {
        mvc.perform(get("/api/insert")).andExpect(status().isOk());
    }
}
