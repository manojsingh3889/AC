package org.crealytics.test.unit;

import org.crealytics.AdDetail;
import org.crealytics.AdDetailRepo;
import org.crealytics.FrontController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FrontController.class)
public class APITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdDetailRepo repo;

    @Before
    public void setUp(){
        AdDetail a = new AdDetail();
//        Mockito.when(repo.save(a)).thenReturn(a);
    }

    @Test
    public void test_insertFun_as_rest() throws Exception {
        given(repo.save(new AdDetail())).willReturn(new AdDetail());
        mockMvc.perform(get("/api/insert")).andExpect(status().isOk());
    }

}
