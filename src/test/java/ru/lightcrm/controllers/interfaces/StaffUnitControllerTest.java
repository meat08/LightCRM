package ru.lightcrm.controllers.interfaces;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.lightcrm.services.interfaces.StaffUnitService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StaffUnitControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private StaffUnitService staffUnitService;

  private final static List<String> unitNames = List.of("unitName1", "unitName2");

  @Test
  @WithMockUser(username = "Bob", authorities = "ADMIN")
  void getAllStaffUnitNames() throws Exception {
    given(staffUnitService.getNames()).willReturn(unitNames);

    mvc.perform(get("/api/v1/staff_units/names")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0]", is("unitName1")))
        .andExpect(jsonPath("$[1]", is("unitName2")));
  }
}
