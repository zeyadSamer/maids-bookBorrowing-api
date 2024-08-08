package com.example.maids_project.controllers;

import com.example.maids_project.entities.Book;
import com.example.maids_project.entities.BorrowingRecord;
import com.example.maids_project.entities.BorrowingRecordDTO;
import com.example.maids_project.entities.Patron;
import com.example.maids_project.repositories.TestingUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BorrowingRecordControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper=new ObjectMapper();
    private void createAndSaveTestBook() throws Exception {
        Book book = TestingUtils.createTestBook();
        String jsonBook=this.objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBook)

        );
    }
    private void createAndSaveTestPatron() throws Exception {
        Patron patron = TestingUtils.createTestPatron();
        String jsonPatron=this.objectMapper.writeValueAsString(patron);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/patrons/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPatron)
        );
    }

    @Test
    public void testThatCreateBorrowingRecordisSuccessfullyReturned() throws Exception {
       Book book = TestingUtils.createTestBook();
       Patron patron=TestingUtils.createTestPatron();
       BorrowingRecord borrowingRecord=TestingUtils.createTestBorrowingRecord(book,patron);
       String jsonBorrowingRecord=objectMapper.writeValueAsString(borrowingRecord);
       createAndSaveTestPatron();
       createAndSaveTestBook();
        System.out.println(borrowingRecord);
        System.out.println(objectMapper.writeValueAsString(borrowingRecord));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/borrow/123/patron/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBorrowingRecord)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.returned").value(false)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.borrowingDate").value(borrowingRecord.getBorrowingDate())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.returnDate").value(borrowingRecord.getReturnDate())
        );

    }

    @Test
    public void testThatBookCanBeReturnedAndOperationAccepted() throws Exception {
        Book book = TestingUtils.createTestBook();
        Patron patron=TestingUtils.createTestPatron();
        BorrowingRecord borrowingRecord=TestingUtils.createTestBorrowingRecord(book,patron);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/return/123/patron/1")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isAccepted()

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.returned").value(true)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.borrowingDate").value(borrowingRecord.getBorrowingDate())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.returnDate").value(borrowingRecord.getReturnDate())
        );

    }

    @Test
    public void testThatNotFoundIsReturnedInCaseOfInvalidIsbnOrPatronId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/return/1232/patron/21")
                        .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(
                MockMvcResultMatchers.status().isNotFound());

    }






}
