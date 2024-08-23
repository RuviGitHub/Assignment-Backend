package lk.icbt.demo.controller;

import lk.icbt.demo.dto.ContactDTO;
import lk.icbt.demo.dto.InquiryDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.service.ContactService;
import lk.icbt.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createContact(@RequestBody ContactDTO contactDTO) {
        ResponseDTO response = contactService.createContact(contactDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllContacts() {
        ResponseDTO response = contactService.getAllContacts();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
