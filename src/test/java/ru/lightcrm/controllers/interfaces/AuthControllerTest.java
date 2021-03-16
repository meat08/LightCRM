package ru.lightcrm.controllers.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.lightcrm.utils.JwtRequest;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createAuthToken() throws Exception {
    StringWriter writer = new StringWriter();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(writer, new JwtRequest("username", "password"));
    mockMvc.perform(MockMvcRequestBuilders.post("/auth")
        .contentType(MediaType.APPLICATION_JSON)
        .content(writer.toString()))
        .andExpect(MockMvcResultMatchers.status().is(401));
  }
}