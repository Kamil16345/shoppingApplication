package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.entity.Reservation;
import com.hotelmanagement.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ReservationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationDate reservationDate;
    public List<Reservation> getAll(){
        return jdbcTemplate
                .query("SELECT * FROM reservation", BeanPropertyRowMapper.newInstance(Reservation.class));
    }
    public Reservation getById(int id){
        return jdbcTemplate
                .queryForObject("SELECT id, start_date, end_date, user_id, hotel_id, room_id FROM reservation WHERE id=?", BeanPropertyRowMapper.newInstance(Reservation.class), id);
    }
    public int save(List<Reservation> reservations){
        reservations.forEach(reservation->jdbcTemplate
                .update("INSERT INTO reservation(start_date, end_date, user_id, hotel_id, room_id) VALUES (?, ?, ?, ?, ?)", reservation.getStart_date(), reservation.getEnd_date(), reservation.getUser_id(), reservation.getHotel_id(), reservation.getRoom_id()));
        return 1;
    }
    public int update(Reservation reservation){
        return jdbcTemplate.update("UPDATE reservation SET start_date=?, end_date=?, user_id=?, hotel_id=?, room_id=?", reservation.getStart_date(), reservation.getEnd_date(), reservation.getUser_id(), reservation.getHotel_id(), reservation.getRoom_id());
    }
    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM reservation WHERE id=?", id);
    }

    public boolean isRoomAvailableForBooking(int hotelId, int roomId, Date startDate, Date endDate) {
        //TODO: currently if first record is false, then it push book. Fix that to push value when checked all fields.
        List<Reservation> reservationDates = reservationDate.getDates(hotelId, roomId);

        for(Reservation reservationDate: reservationDates){
            System.out.println(reservationDate.getStart_date());
            System.out.println(reservationDate.getEnd_date());
            if((startDate.compareTo(reservationDate.getStart_date())>=0 && startDate.compareTo(reservationDate.getEnd_date())<=0)
                || (endDate.compareTo(reservationDate.getStart_date())>=0 && endDate.compareTo(reservationDate.getEnd_date())<=0)){
                System.out.println("This date is book already.");
                return false;
            }else{
                return true;
            }
        }
        return true;
    }
}
