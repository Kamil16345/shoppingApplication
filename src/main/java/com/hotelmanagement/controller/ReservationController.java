package com.hotelmanagement.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotelmanagement.entity.Reservation;
import com.hotelmanagement.entity.Room;
import com.hotelmanagement.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    public String fieldName;
    @Autowired
    ReservationRepository reservationRepository;
    @GetMapping("")
    public List<Reservation> getAll(){return reservationRepository.getAll();}
    @GetMapping("/{id}")
    public Reservation getById(@PathVariable("id")int id){return reservationRepository.getById(id);}
    @PostMapping("")
    public int add(@RequestBody List<Reservation>reservations){
        //System.out.println(reservations);
        for (Reservation reservation: reservations){
            if(reservationRepository.isRoomAvailableForBooking(reservation.getHotel_id(), reservation.getRoom_id(), reservation.getStart_date(), reservation.getEnd_date())){
                System.out.println("Date available!");
                return reservationRepository.save(reservations);
            }else{
                return -1;
            }
        }

        return -1;
    }
    @PutMapping("/{id}")
    public int update(@PathVariable("id")int id, @RequestBody Reservation updatedReservation){
        Reservation reservation = reservationRepository.getById(id);
        if(reservation!=null){
            reservation.setStart_date(updatedReservation.getStart_date());
            reservation.setEnd_date(updatedReservation.getEnd_date());
            reservation.setUser_id(updatedReservation.getUser_id());
            reservation.setHotel_id(updatedReservation.getRoom_id());
            reservation.setRoom_id(updatedReservation.getRoom_id());
            reservationRepository.update(reservation);
            return 1;
        }else{
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int updatePartially(@PathVariable("id")int id, @RequestBody Reservation updatedReservation){
        Reservation reservation = reservationRepository.getById(id);
        if(reservation!=null){
            if(updatedReservation.getStart_date()!=null){updatedReservation.setStart_date(reservation.getStart_date());}
            if(updatedReservation.getEnd_date()!=null){updatedReservation.setEnd_date(reservation.getEnd_date());}
            if(updatedReservation.getUser_id()!=0){updatedReservation.setUser_id(reservation.getUser_id());}
            if(updatedReservation.getHotel_id()!=0){updatedReservation.setHotel_id(reservation.getHotel_id());}
            if(updatedReservation.getRoom_id()!=0){updatedReservation.setRoom_id(reservation.getRoom_id());}
            reservationRepository.update(reservation);
            return 1;
        }else{
            return-1;
        }

    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id")int id){return reservationRepository.delete(id);}
}
