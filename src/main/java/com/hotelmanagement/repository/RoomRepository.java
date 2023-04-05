package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Room> getAll(){
        return jdbcTemplate.query("SELECT * FROM room", BeanPropertyRowMapper.newInstance(Room.class));
    }
    public Room getById(int id){
        return jdbcTemplate
                .queryForObject("SELECT id, room_number, room_type_id, hotel_id FROM room WHERE id=?", BeanPropertyRowMapper.newInstance(Room.class), id);
    }
    public int save(List<Room> rooms){
        rooms.forEach(room -> jdbcTemplate
                .update("INSERT INTO room(room_number, room_type_id, hotel_id) VALUES (?, ?, ?)", room.getRoom_number(), room.getRoom_type_id(), room.getHotel_id()));
        return 0;
    }
    public int update(Room room){
        return jdbcTemplate.update("UPDATE room SET room_number=?, room_type_id=?, hotel_id=?", room.getRoom_number(), room.getRoom_type_id(), room.getHotel_id());
    }
    public int delete(int id){
        return jdbcTemplate.update("DELETE FROM room WHERE id=?", id);
    }
}
