package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

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
}
