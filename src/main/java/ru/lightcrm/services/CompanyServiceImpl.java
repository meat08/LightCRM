package ru.lightcrm.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Company;
import ru.lightcrm.entities.Contact;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.dtos.CompanyDto;
import ru.lightcrm.entities.dtos.ContactDto;
import ru.lightcrm.entities.dtos.ProfileMiniDto;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.CompanyRepository;
import ru.lightcrm.services.interfaces.CompanyService;
import ru.lightcrm.services.interfaces.ContactService;
import ru.lightcrm.services.interfaces.ProfileService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companiesRepository;
    private ContactService contactService;
    private ProfileService profileService;

    @Autowired
    public void setCompaniesRepository(CompanyRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public CompanyDto findByName(String name) {
        return new CompanyDto(companiesRepository.findOneByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Компания '%s' не найдена", name))));
    }

    public CompanyDto findByInn(Long inn) {
        return new CompanyDto(companiesRepository.findOneByInn(inn)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с ИНН '%s' не найдена", inn))));
    }

    public CompanyDto findById(Long id) {
        return new CompanyDto(companiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с id '%s' не найдена", id))));
    }

    @Override
    public Company findEntityById(Long id) {
        return companiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с id '%s' не найдена", id)));
    }

    public List<CompanyDto> findAllDTO() {
        return companiesRepository.findAll().stream().map(CompanyDto::new).collect(Collectors.toList());
    }

    @Override
    public CompanyDto save(CompanyDto companyDto) {
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setType(company.isType());
        company.setInn(companyDto.getInn());
        company.setBillNumber(companyDto.getBillNumber());
        company.setPhoneNumber(companyDto.getPhoneNumber());
        company.setEmail(companyDto.getEmail());

        Set<Contact> contacts = new HashSet<>();
        for (ContactDto contactDto : companyDto.getContacts()) {
            Contact contact = new Contact();
            contact.setName(contactDto.getName());
            contact.setPost(contactDto.getPost());
            contact.setPhone(contactDto.getPhone());
            contact.setEmail(contactDto.getEmail());
            contact.setDescription(contactDto.getDescription());
            contact.setCompany(company);
            contacts.add(contact);
        }
        company.setContacts(contacts);

        Set<Profile> managers = companyDto.getManagers().stream()
                .map(m -> profileService.findById(m.getId())).collect(Collectors.toSet());
        company.setManagers(managers);

        company.setComments(null);

        return new CompanyDto(companiesRepository.save(company));
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) {
        Company company = companiesRepository.findById(companyDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Компания с id '%s' не найдена", companyDto.getId())));
        company.setName(companyDto.getName());
        company.setType(company.isType());
        company.setInn(companyDto.getInn());
        company.setBillNumber(companyDto.getBillNumber());
        company.setPhoneNumber(companyDto.getPhoneNumber());
        company.setEmail(companyDto.getEmail());

        Set<Profile> managers = companyDto.getManagers().stream()
                .map(m -> profileService.findById(m.getId())).collect(Collectors.toSet());
        company.setManagers(managers);

        // TODO обновление существующих контактов и удаление ненужных
        // company.setContacts(null);

        return new CompanyDto(companiesRepository.save(company));
    }

    @Override
    public void deleteById(Long id) {
        companiesRepository.deleteById(id);
    }
}
