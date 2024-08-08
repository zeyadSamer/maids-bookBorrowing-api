package com.example.maids_project.controllers;

import com.example.maids_project.entities.Book;
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
public class BookControllerIntegrationTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper=new ObjectMapper();




    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnCreatedBook() throws Exception {
        Book book = TestingUtils.createTestBook();
        String jsonBook=this.objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.publicationYear").value(book.getPublicationYear())
        );


    }


    @Test
    public void testThatABookCanBeReturnedSuccessfully() throws Exception {
        Book book = TestingUtils.createTestBook();
        String jsonBook=this.objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/123")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()

        );
    }

    @Test
    public void testThatNotFoundBookIdReturnsNotFound() throws Exception {


        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/456")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()

        );

    }


    @Test
    public void  testThatBookCanBeUpdatedAndReturnedSuccessfully() throws Exception {

        Book book = TestingUtils.createTestBook();

        String jsonBook=this.objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        );

        book.setAuthor("Changed Author");
        jsonBook=this.objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/books/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.author").value("Changed Author")
        )
        ;
    }

    @Test
    public void testThatBookCanBeDeletedAndBeReturnedSuccessfully() throws Exception {

        Book book = TestingUtils.createTestBook();

        String jsonBook=this.objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/books/123")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isAccepted()

        );



    }

}
