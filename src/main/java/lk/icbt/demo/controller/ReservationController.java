package lk.icbt.demo.controller;

import lk.icbt.demo.dto.ReservationDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ResponseDTO response = reservationService.createReservation(reservationDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllReservations() {
        ResponseDTO response = reservationService.getAllReservations();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateReservation(@PathVariable Long id) {
        System.out.println(id);
        ResponseDTO response = reservationService.updateReservation(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
