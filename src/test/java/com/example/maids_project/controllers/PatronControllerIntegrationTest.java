package com.example.maids_project.controllers;

import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.Patron;
import com.example.maids_project.repositories.TestingUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PatronControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper=new ObjectMapper();




    @Autowired
    public PatronControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCreatePatronSuccessfullyReturnsPatron() throws Exception {
        Patron patron = TestingUtils.createTestPatron();
        String jsonPatron=this.objectMapper.writeValueAsString(patron);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatron)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(patron.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(patron.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.phoneNumber").value(patron.getPhoneNumber())
        );

    }


    @Test
    public void testThatPatronCanBeReturnedSuccessfully() throws Exception {
        Patron patron = TestingUtils.createTestPatron();
        String jsonBook=this.objectMapper.writeValueAsString(patron);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()

        );
    }

    @Test
    public void testThatNotFoundPatronIdReturnsNotFound() throws Exception {


        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/patrons/456")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()

        );

    }


    @Test
    public void  testThatPatronCanBeUpdatedAndReturnedSuccessfully() throws Exception {

        Patron patron = TestingUtils.createTestPatron();

        String jsonPatron=this.objectMapper.writeValueAsString(patron);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatron)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        );

        patron.setName("Changed Name");
        jsonPatron=this.objectMapper.writeValueAsString(patron);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatron)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name ").value("Changed Name")
        )
        ;
    }

    @Test
    public void testThatPatronCanBeDeletedAndBeReturnedSuccessfully() throws Exception {

        Patron patron = TestingUtils.createTestPatron();

        String jsonPatron=this.objectMapper.writeValueAsString(patron);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatron)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isAccepted()

        );



    }

}
