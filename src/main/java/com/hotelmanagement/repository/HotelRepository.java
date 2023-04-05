package com.hotelmanagement.repository;

import com.hotelmanagement.entity.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Hotel> getAll(){
        return jdbcTemplate
                .query("SELECT id, name, stars, city FROM hotel", BeanPropertyRowMapper.newInstance(Hotel.class));
    }

    public Hotel getById(int id){
        return jdbcTemplate.
                queryForObject("SELECT id, name, stars, city FROM hotel WHERE id = ? ", BeanPropertyRowMapper.newInstance(Hotel.class), id);
    }

    public int save(List<Hotel> hotels){
        hotels.forEach(hotel -> jdbcTemplate
                .update("INSERT INTO hotel(name, stars, city) VALUES (?, ?, ?)", hotel.getName(), hotel.getStars(), hotel.getStars()));
        return 0;
    }
    public int update(Hotel hotel){
        return jdbcTemplate
                .update("UPDATE hotel SET name=?, stars=?, city=?", hotel.getName(), hotel.getStars(), hotel.getCity());
    }
}
