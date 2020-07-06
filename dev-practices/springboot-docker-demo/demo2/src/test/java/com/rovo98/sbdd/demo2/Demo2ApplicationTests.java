package com.rovo98.sbdd.demo2;

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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class Demo2ApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void givenMockMvc_whenPerformingHelloGetRequest_thenReturnSpecifiedStringAsResult() throws Exception {
        String testName = "rovo98";
        MvcResult result = mvc.perform(get("/app/sayHello/" + testName)
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Assert.assertNotNull(response);

//        Assert.assertEquals("Hello rovo98 from demo2.", response);
        Assert.assertTrue(response.startsWith("Hello rovo98"));
    }

}
