package com.app.demo.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;



    @Test
    public void testOauthGetALL() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/oauth/api/user/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testOauthGetByUsername() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/oauth/api/user/get/1234567890"))
                .andExpect(status().isOk());
    }

    @Test
    public void testClientGetNotLoggedIn() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/api/client/getAgent/06060606"))
                .andExpect(status().is(401));

    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"AGENT", "SUPER"})
    public void testClientGetLoggedIn() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/api/client/getAgent/06060606"))
                .andExpect(status().is(401));
    }


    @Test
    public void testAgent() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/agent"))
                .andExpect(status().is(200));

    }

    @Test
    public void testAccount() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/api/account"))
                .andExpect(status().is(401));
    }

    @Test
    public void testAgency() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/agency"))
                .andExpect(status().is(200));
    }



    @Test
    public void testFakeServiceINWI() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/inwi"))
                .andExpect(status().is(401));
    }

    @Test
    public void testNoneAuthorizedRequest() throws Exception {
        this.mockMvc =
                MockMvcBuilders
                        .webAppContextSetup(this.webApplicationContext)
                        .apply(springSecurity())
                        .build();
        mockMvc.perform(get("/random"))
                .andExpect(status().is(401));
    }






}