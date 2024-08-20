package lk.icbt.demo.controller;

import lk.icbt.demo.dto.InquiryDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inquiries")
@CrossOrigin
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInquiry(@RequestBody InquiryDTO inquiryDTO) {
        ResponseDTO response = inquiryService.createInquiry(inquiryDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllInquiries() {
        ResponseDTO response = inquiryService.getAllInquiries();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
