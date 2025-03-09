package aidid.library.test;

import aidid.library.controller.BookController;
import aidid.library.model.Book;
import aidid.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterBook() throws Exception {
        Book book = new Book();
        book.setIsbn("978-3-16-148410-0");
        book.setTitle("Example Book");
        book.setAuthor("Jane Doe");

        when(bookService.registerBook(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$.title").value("Example Book"))
                .andExpect(jsonPath("$.author").value("Jane Doe"));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setIsbn("978-3-16-148410-0");
        book.setTitle("Example Book");
        book.setAuthor("Jane Doe");

        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$[0].title").value("Example Book"))
                .andExpect(jsonPath("$[0].author").value("Jane Doe"));
    }
}