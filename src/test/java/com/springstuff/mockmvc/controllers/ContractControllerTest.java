package com.springstuff.mockmvc.controllers;

import com.springstuff.mockmvc.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//provides Spring Text Context Framework
//preparing WebApplicationContext
//@RunWith(SpringRunner.class) tells JUnit to run using Spring’s testing support.
//SpringRunner is the new name for SpringJUnit4ClassRunner, it’s just a bit easier on the eye.
//read https://dzone.com/articles/testing-improvements-in-spring-boot-14
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class ContractControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    //creating interface to run the test to allow test to interact with the endpoints
    //mocking the Spring MVC platform - not mocking controller, just putting controller inside the mocked MVC platform and testing it
    private MockMvc mockMvc;

    /**
     * This creates a fully fledge WebApplicationContext and can tell mockMvc about the controllers
     * that are present in the application context
     * MockMvc is a utility class to interact with the application that is exposed in the app context
     * MockMvc can be used to write test more easily to interact with the controller
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void contractReturnsCorrectHttpStatusAndPayload() throws Exception {
        String getContractUrl = new StringBuilder()
                                    .append("/")
                                    .append(ContractController.CONTRACT_URI)
                                    .append("/{contractNumber}")
                                    .toString();

        int contractNumber = 345;
        mockMvc.perform(get(getContractUrl, contractNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"name\":\"jay\",\"contractNumber\":345}", true))
                .andDo(print())
                .andReturn();
    }

}