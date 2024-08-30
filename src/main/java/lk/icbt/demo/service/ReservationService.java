package lk.icbt.demo.service;

import lk.icbt.demo.dto.ReservationDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.entity.Order;
import lk.icbt.demo.entity.Reservation;
import lk.icbt.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setCustomerName(reservationDTO.getCustomerName());
        reservation.setDate(reservationDTO.getDate());
        reservation.setHeadCount(reservationDTO.getHeadCount());
        reservation.setBuffet(Reservation.Buffet.valueOf(reservationDTO.getBuffet()));
        reservation.setBranch(Reservation.Branch.valueOf(reservationDTO.getBranch()));
        reservation.setStatus(Reservation.Status.valueOf(reservationDTO.getStatus()));
        reservation.setIsDeleted(false);

        Reservation createdReservation = reservationRepository.save(reservation);
        return new ResponseDTO(201, "Message: Reservation created.", createdReservation);
    }

    public ResponseDTO getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll()
                .stream()
                .filter(reservation -> !reservation.getIsDeleted())
                .collect(Collectors.toList());

        return new ResponseDTO(200, "Message: Reservations fetched.", reservations);
    }

    public ResponseDTO updateReservation(Long id) {
        // Check if  exists

        Optional<Reservation> existingReservationOpt = reservationRepository.findById(id);

        if (!existingReservationOpt.isPresent()) {
            return new ResponseDTO(404, "Error: Reservation not found.", null);
        }

        Reservation existingReservation = existingReservationOpt.get();

        // Update  details
        existingReservation.setStatus(Reservation.Status.COMPLETED);

        // Save the updated user information
        Reservation updatedReservation = reservationRepository.save(existingReservation);

        // Return a success response
        return new ResponseDTO(200, "Message: Reservation confirmed.", updatedReservation);
    }
}

