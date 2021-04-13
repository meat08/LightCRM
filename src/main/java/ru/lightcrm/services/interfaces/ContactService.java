package ru.lightcrm.services.interfaces;

import ru.lightcrm.entities.Contact;
import ru.lightcrm.entities.dtos.ContactDto;

public interface ContactService {
    ContactDto findById(Long id);
    Contact findEntityById(Long id);
    ContactDto saveOrUpdate(ContactDto contactDto);
    void delete(Long id);
}
