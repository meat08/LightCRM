package ru.lightcrm.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Contact;
import ru.lightcrm.entities.dtos.ContactDto;
import ru.lightcrm.repositories.ContactRepository;
import ru.lightcrm.services.interfaces.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ContactServiceTest {
    @Autowired
    private ContactService contactService;

    @MockBean
    private ContactRepository contactRepository;

    private static final String COMPANY_NAME = "Газпром";
    private static final String COMPANY_POST = "Москва, Арбат";
    private static final String COMPANY_PHONE = "+79999992324";
    private static final String COMPANY_EMAIL = "gazprom@gazprom.ru";
    private static final String COMPANY_DESCRIPTION = "Description";
    private static final int CONTACT_COUNT = 3;

    @BeforeEach
    void init() {
        List<Contact> contacts = new ArrayList<>();

        for (long i = 1; i <= CONTACT_COUNT; i++) {
            Contact contact = new Contact();
            contact.setId(i);
            contact.setName(COMPANY_NAME + " " + i);
            contact.setPost(COMPANY_POST);
            contact.setPhone(COMPANY_PHONE);
            contact.setEmail(COMPANY_EMAIL);
            contact.setDescription(COMPANY_DESCRIPTION);

            Company company = new Company();
            company.setId(1L);
            contact.setCompany(company);

            contacts.add(contact);
        }

        Mockito.doReturn(Optional.of(contacts.get(0))).when(contactRepository).findById(contacts.get(0).getId());
    }

    @Test
    void findByIdTest() {
        Long id = 1L;

        ContactDto contactDto = contactService.findById(id);
        Assertions.assertNotNull(contactDto);
        Assertions.assertEquals(id, contactDto.getId());
        Assertions.assertEquals(COMPANY_NAME + " " + id, contactDto.getName());
        Assertions.assertEquals(COMPANY_POST, contactDto.getPost());
        Assertions.assertEquals(COMPANY_PHONE, contactDto.getPhone());
        Assertions.assertEquals(COMPANY_DESCRIPTION, contactDto.getDescription());
        Assertions.assertEquals(COMPANY_EMAIL, contactDto.getEmail());
    }
}
