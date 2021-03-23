package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Contact;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.dtos.CompanyDto;
import ru.lightcrm.repositories.CompanyRepository;
import ru.lightcrm.services.interfaces.CompanyService;

import java.util.*;


@SpringBootTest
@ActiveProfiles("test")
class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    private static final String COMPANY_NAME = "Газпром";
    private static final boolean COMPANY_TYPE = true;
    private static final Long COMPANY_INN = 50282517112359L;
    private static final Long COMPANY_BILL_NUMBER = 50282517112359L;
    private static final String COMPANY_PHONE_NUMBER = "+79999992324";
    private static final String COMPANY_EMAIL = "gazprom@gazprom.ru";
    private static final int COMPANY_COUNT = 3;

    private Optional<Company> initMockCompany(Long id) {
        Company company = new Company();
        company.setId(id);
        company.setName(COMPANY_NAME + " " + id);
        company.setType(COMPANY_TYPE);
        company.setInn(COMPANY_INN);
        company.setBillNumber(COMPANY_BILL_NUMBER);
        company.setPhoneNumber(COMPANY_PHONE_NUMBER);
        company.setEmail(COMPANY_EMAIL);

        Set<Contact> contacts = new HashSet<>(1);
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("TEST");
        contacts.add(contact);
        company.setContacts(contacts);

//        List<Profile> profiles = new ArrayList<>(1);
//        Profile profile = new Profile();
//        profile.setId(1L);
//        profiles.add(profile);
//        company.setManagers(profiles);

        return Optional.of(company);
    }

    private List<Company> initMockCompanyList(){
        List<Company> companyList = new ArrayList<>();
        for(int i = 0; i < COMPANY_COUNT; i++) companyList.add(initMockCompany((long)(i + 1)).orElse(null));

        return companyList;
    }

    @Test
    void findByName() {
        Long id = 1L;
        final String name = COMPANY_NAME + " " + id;
        Mockito.doReturn(initMockCompany(id)).when(companyRepository).findOneByName(name);

        CompanyDto companyDto = companyService.findByName(name);
        Assertions.assertNotNull(companyDto);
        Assertions.assertEquals(id, companyDto.getId());
        Assertions.assertEquals(COMPANY_NAME + " " + id, companyDto.getName());
        Assertions.assertEquals(COMPANY_TYPE, companyDto.isType());
        Assertions.assertEquals(COMPANY_INN, companyDto.getInn());
        Assertions.assertEquals(COMPANY_BILL_NUMBER, companyDto.getBillNumber());
        Assertions.assertEquals(COMPANY_PHONE_NUMBER, companyDto.getPhoneNumber());
        Assertions.assertEquals(COMPANY_EMAIL, companyDto.getEmail());
        Assertions.assertEquals(1, companyDto.getContacts().size());
        //Assertions.assertEquals(1, companyDto.getManagers().size());
    }

    @Test
    void findByInn() {
        Long id = 1L;
        final Long inn = COMPANY_INN;
        Mockito.doReturn(initMockCompany(id)).when(companyRepository).findOneByInn(inn);

        CompanyDto companyDto = companyService.findByInn(inn);
        Assertions.assertNotNull(companyDto);
        Assertions.assertEquals(id, companyDto.getId());
        Assertions.assertEquals(COMPANY_NAME + " " + id, companyDto.getName());
        Assertions.assertEquals(COMPANY_TYPE, companyDto.isType());
        Assertions.assertEquals(COMPANY_INN, companyDto.getInn());
        Assertions.assertEquals(COMPANY_BILL_NUMBER, companyDto.getBillNumber());
        Assertions.assertEquals(COMPANY_PHONE_NUMBER, companyDto.getPhoneNumber());
        Assertions.assertEquals(COMPANY_EMAIL, companyDto.getEmail());
        Assertions.assertEquals(1, companyDto.getContacts().size());
        //Assertions.assertEquals(1, companyDto.getManagers().size());
    }

    @Test
    void findById() {
        Long id = 1L;
        Mockito.doReturn(initMockCompany(id)).when(companyRepository).findOneById(id);

        CompanyDto companyDto = companyService.findById(id);
        Assertions.assertNotNull(companyDto);
        Assertions.assertEquals(id, companyDto.getId());
        Assertions.assertEquals(COMPANY_NAME + " " + id, companyDto.getName());
        Assertions.assertEquals(COMPANY_TYPE, companyDto.isType());
        Assertions.assertEquals(COMPANY_INN, companyDto.getInn());
        Assertions.assertEquals(COMPANY_BILL_NUMBER, companyDto.getBillNumber());
        Assertions.assertEquals(COMPANY_PHONE_NUMBER, companyDto.getPhoneNumber());
        Assertions.assertEquals(COMPANY_EMAIL, companyDto.getEmail());
        Assertions.assertEquals(1, companyDto.getContacts().size());
        //Assertions.assertEquals(1, companyDto.getManagers().size());
    }

    @Test
    void findAllDTO() {
        Mockito.doReturn(initMockCompanyList()).when(companyRepository).findAll();

        List<CompanyDto> companyDtoList = companyService.findAllDTO();
        Assertions.assertNotNull(companyDtoList);
        Assertions.assertEquals(COMPANY_COUNT, companyDtoList.size());
        Assertions.assertEquals(COMPANY_NAME + " 1", companyDtoList.get(0).getName());
    }
}