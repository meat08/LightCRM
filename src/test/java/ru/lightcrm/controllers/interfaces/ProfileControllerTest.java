package ru.lightcrm.controllers.interfaces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfileService profileService;

    private static List<ProfileDto> testProfileDtoList;
    private static ProfileDto testProfileDto;

    @BeforeAll
    public static void init() {
        testProfileDto = new ProfileDto();
        testProfileDto.setId(1L);
        testProfileDto.setFirstname("Тест");
        testProfileDto.setLastname("Тестов");
        testProfileDto.setMiddlename("Тестович");
        testProfileDto.setPhone("89997776655");
        testProfileDto.setEmail("test@email.com");
        testProfileDto.setSex("M");
        testProfileDtoList = List.of(testProfileDto);
    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getAllProfilesTest() throws Exception {
        given(profileService.findAll()).willReturn(testProfileDtoList);

        mvc.perform(get("/api/v1/profiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is(testProfileDtoList.get(0).getFirstname())))
                .andExpect(jsonPath("$[0].lastname", is(testProfileDto.getLastname())))
                .andExpect(jsonPath("$[0].middlename", is(testProfileDto.getMiddlename())));
    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getProfileTest() throws Exception {
        given(profileService.findById(1L)).willReturn(testProfileDto);

        mvc.perform(get("/api/v1/profiles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is(testProfileDto.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(testProfileDto.getLastname())))
                .andExpect(jsonPath("$.middlename", is(testProfileDto.getMiddlename())));
    }
}
