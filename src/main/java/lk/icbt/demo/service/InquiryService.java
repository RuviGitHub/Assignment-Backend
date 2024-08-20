package lk.icbt.demo.service;

import lk.icbt.demo.dto.InquiryDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.entity.Inquiry;
import lk.icbt.demo.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public ResponseDTO createInquiry(InquiryDTO inquiryDTO) {
        Inquiry inquiry = new Inquiry();
        inquiry.setCustomerId(inquiryDTO.getCustomerId());
        inquiry.setDate(inquiryDTO.getDate());
        inquiry.setDescription(inquiryDTO.getDescription());
        inquiry.setResponse(inquiryDTO.getResponse());
        inquiry.setStatus(Inquiry.Status.valueOf(inquiryDTO.getStatus()));
        inquiry.setIsDeleted(false);

        Inquiry createdInquiry = inquiryRepository.save(inquiry);
        return new ResponseDTO(201, "Message: Inquiry created.", createdInquiry);
    }

    public ResponseDTO getAllInquiries() {
        List<Inquiry> inquiries = inquiryRepository.findAll()
                .stream()
                .filter(inquiry -> !inquiry.getIsDeleted())
                .collect(Collectors.toList());

        return new ResponseDTO(200, "Message: Inquiries fetched.", inquiries);
    }
}
