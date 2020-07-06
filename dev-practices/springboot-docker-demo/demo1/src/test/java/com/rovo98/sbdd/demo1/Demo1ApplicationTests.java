package com.rovo98.sbdd.demo1;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class Demo1ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenMockMvc_whenPerformSayHelloGetRequest_thenReturnTheSpecifiedStringAsResult() throws Exception {
        String testName = "rovo98";
        MvcResult result = mvc.perform(get("/app/sayHello/" + testName)
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();

        Assert.assertNotNull(resp);
//        Assert.assertEquals("Hello rovo98 from demo1.", resp);
        Assert.assertTrue(resp.startsWith("Hello rovo98"));
    }

}
