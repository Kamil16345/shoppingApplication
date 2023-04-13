package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Reservation;
import com.hotelmanagement.repository.RoomRepository;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
@Getter
public class ReservationDate {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    private Date startDate;
    private Date endDate;

    public List<Reservation> getDates(int hotelId, int roomId){
        return jdbcTemplate
                .query("SELECT start_date, end_date FROM reservation WHERE hotel_id=? AND room_id=?", BeanPropertyRowMapper.newInstance(Reservation.class),hotelId, roomId);
    }
}
