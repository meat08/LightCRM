package ru.lightcrm.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Contact;
import ru.lightcrm.entities.dtos.ContactDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.ContactRepository;
import ru.lightcrm.services.interfaces.CompanyService;
import ru.lightcrm.services.interfaces.ContactService;

import java.util.Set;

@Service
@NoArgsConstructor
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private CompanyService companyService;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public ContactDto findById(Long id) {
        return new ContactDto(contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Контакт с Id %s не найден.", id))));
    }

    @Override
    public Contact findEntityById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Контакт с Id %s не найден.", id)));
    }

    @Override
    public ContactDto saveOrUpdate(ContactDto contactDto) {
        Contact contact;
        if (contactDto.getId() == null) {
            contact = new Contact();
        } else {
            contact = contactRepository.findById(contactDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Контакт с Id %s не найден.", contactDto.getId())));
        }
        contact.setName(contactDto.getName());
        contact.setPost(contactDto.getPost());
        contact.setPhone(contactDto.getPhone());
        contact.setEmail(contactDto.getEmail());
        contact.setDescription(contactDto.getDescription());
        contact.setCompany(null);

        return new ContactDto(contactRepository.save(contact));
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
