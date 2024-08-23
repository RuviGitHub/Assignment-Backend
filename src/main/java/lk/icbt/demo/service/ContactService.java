package lk.icbt.demo.service;

import lk.icbt.demo.dto.ContactDTO;
import lk.icbt.demo.dto.InquiryDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.entity.Contact;
import lk.icbt.demo.entity.Inquiry;
import lk.icbt.demo.repository.ContactRepository;
import lk.icbt.demo.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ResponseDTO createContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setEmail(contactDTO.getEmail());
        contact.setName(contactDTO.getName());
        contact.setMessage(contactDTO.getMessage());
        contact.setIsDeleted(false);

        Contact createdContact = contactRepository.save(contact);
        return new ResponseDTO(201, "Message: Contact created.", createdContact);
    }

    public ResponseDTO getAllContacts() {
        List<Contact> contacts = contactRepository.findAll()
                .stream()
                .filter(contact -> !contact.getIsDeleted())
                .collect(Collectors.toList());

        return new ResponseDTO(200, "Message: Contacts fetched.", contacts);
    }
}
