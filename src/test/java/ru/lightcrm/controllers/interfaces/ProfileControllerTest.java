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
import ru.lightcrm.entities.dtos.ProfileFullDto;
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
    private static List<ProfileFullDto> testProfileFullDtoList;
    private static ProfileDto testProfileDto;
    private static ProfileFullDto testProfileFullDto;

    @BeforeAll
    public static void prepareTestData() {
        testProfileDto = new ProfileDto();
        testProfileDto.setId(1L);
        testProfileDto.setFirstname("Тест");
        testProfileDto.setLastname("Тестов");
        testProfileDto.setMiddlename("Тестович");
        testProfileDtoList = List.of(testProfileDto);
        // Full
        testProfileFullDto = new ProfileFullDto();
        testProfileFullDto.setSex("M");
        testProfileFullDto.setPhone("80001112233");
        testProfileFullDto.setEmail("test@email.com");
        testProfileFullDtoList = List.of(testProfileFullDto);

    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getAllProfilesDtoTest() throws Exception {
        given(profileService.findAll()).willReturn(testProfileDtoList);

        mvc.perform(get("/api/v1/profiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is(testProfileDtoList.get(0).getFirstname())))
                .andExpect(jsonPath("$[0].lastname", is(testProfileDtoList.get(0).getLastname())))
                .andExpect(jsonPath("$[0].middlename", is(testProfileDtoList.get(0).getMiddlename())));
    }

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getAllProfilesFullDtoTest() throws Exception {
        given(profileService.findFullAll()).willReturn(testProfileFullDtoList);

        mvc.perform(get("/api/v1/profiles/full")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].sex", is(testProfileFullDtoList.get(0).getSex())))
                .andExpect(jsonPath("$[0].phone", is(testProfileFullDtoList.get(0).getPhone())))
                .andExpect(jsonPath("$[0].email", is(testProfileFullDtoList.get(0).getEmail())));
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

    @Test
    @WithMockUser(username = "Bob", authorities = "ADMIN")
    public void getProfileFullTest() throws Exception {
        given(profileService.findFullById(1L)).willReturn(testProfileFullDto);

        mvc.perform(get("/api/v1/profiles/full/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sex", is(testProfileFullDto.getSex())))
                .andExpect(jsonPath("$.phone", is(testProfileFullDto.getPhone())))
                .andExpect(jsonPath("$.email", is(testProfileFullDto.getEmail())));
    }
}
