package com.mycompagny.produit.controllers.api;

import com.mycompagny.produit.repository.ProduitRepository;
import com.mycompagny.produit.service.ProduitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest(ProduitControllerRest.class)
public class ProduitControllerRestTest {

    @Autowired
    private MockMvc mockMvc;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    //ProduitDto RECORD_2 = new ProduitDto(2L, "Ipad2", 200.0, "New York USA");
    //ProduitDto RECORD_3 = new ProduitDto(3L, "Ipad3", 300.0, "New York USA");

    @Test
    public void getAllProduits_success() throws Exception {

        /*mockMvc.perform(get("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(alex.getName())));*/


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().string(containsString("Ipad2")));

    }
}