package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.*;
import ru.lightcrm.entities.dtos.ProfileDto;
import ru.lightcrm.repositories.ProfileRepository;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @MockBean
    private ProfileRepository profileRepository;

    private static List<Profile> testProfileList;
    private static Profile testProfile;

    @BeforeAll
    public static void init() {
        testProfile = new Profile();
        User testUser = new User();
        testUser.setId(1L);
        testUser.setLogin("TEST_USER");
        testUser.setPriorities(Collections.emptySet());
        StaffUnit testStaff = new StaffUnit();
        testStaff.setId(1L);
        testStaff.setName("TEST_STAFF");
        testStaff.setRoles(Collections.emptyList());
        Company testCompany = new Company();
        testCompany.setId(1L);
        testCompany.setName("TEST_COMPANY");
        Department testDepartment = new Department();
        testDepartment.setId(1L);
        testDepartment.setName("TEST_DEPARTMENT");
        testProfile.setId(1L);
        testProfile.setFirstname("Тест");
        testProfile.setLastname("Тестов");
        testProfile.setMiddlename("Тестович");
        testProfile.setPhone("89997776655");
        testProfile.setEmail("test@email.com");
        testProfile.setSex("M");
        testProfile.setUser(testUser);
        testProfile.setStaffUnit(testStaff);
     //   testProfile.setCompany(testCompany);
        testProfile.setManagedDepartment(testDepartment);
        testProfile.setDepartments(Collections.singletonList(testDepartment));
        testProfileList = List.of(testProfile);
    }

    @Test
    public void findProfileByIdTest() {
        Mockito.doReturn(Optional.of(testProfile))
                .when(profileRepository)
                .findById(testProfile.getId());

        ProfileDto profileDto = profileService.findById(testProfile.getId());

        Assertions.assertNotNull(profileDto);
        Assertions.assertEquals(testProfile.getFirstname(), profileDto.getFirstname());
        Assertions.assertEquals(testProfile.getLastname(), profileDto.getLastname());
        Assertions.assertEquals(testProfile.getMiddlename(), profileDto.getMiddlename());
        Mockito.verify(profileRepository, Mockito.times(1)).findById(testProfile.getId());
    }

    @Test
    public void findAllTest() {
        Mockito.doReturn(testProfileList)
                .when(profileRepository)
                .findAll();

        List<ProfileDto> profileDtos = profileService.findAll();

        Assertions.assertNotNull(profileDtos);
        Assertions.assertEquals(testProfileList.size(), profileDtos.size());
        Assertions.assertEquals(testProfileList.get(0).getFirstname(), profileDtos.get(0).getFirstname());
        Assertions.assertEquals(testProfileList.get(0).getLastname(), profileDtos.get(0).getLastname());
        Assertions.assertEquals(testProfileList.get(0).getMiddlename(), profileDtos.get(0).getMiddlename());
        Mockito.verify(profileRepository, Mockito.times(1)).findAll();
    }
}
