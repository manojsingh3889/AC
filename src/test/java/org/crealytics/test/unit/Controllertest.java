package org.crealytics.test.unit;

import org.crealytics.bean.AdDetail;
import org.crealytics.controller.FrontController;
import org.crealytics.service.AdServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(FrontController.class)
public class Controllertest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdServiceImpl service;

    @Autowired
    FrontController controller;

    @Before
    public void setup(){
        AdDetail a = new AdDetail();
        Mockito.when(service.insert(a)).thenReturn(a);
    }

    @Test
    public void serviceAutoWired() {
        Assert.assertNotNull(controller);
    }

//    @Test
//    public void test_insertFun(){
//        Assert.assertEquals(200,controller.insert().getStatusCode().value());
//    }
//
//    @Test
//    public void test_insertFun_as_rest() throws Exception {
//        mockMvc.perform(get("/api/insert")).andExpect(status().isOk());
//    }

}
