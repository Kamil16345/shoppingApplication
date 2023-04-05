package com.hotelmanagement.controller;

import com.hotelmanagement.entity.Hotel;
import com.hotelmanagement.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @GetMapping("")
    public List<Hotel> getAll(){
        return hotelRepository.getAll();
    }
    @GetMapping("/{id}")
    public Hotel getById(@PathVariable int id){
        return hotelRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Hotel> hotels){
        return hotelRepository.save(hotels);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Hotel updatedHotel){
        Hotel hotel = hotelRepository.getById(id);
        if(hotel!=null){
            hotel.setName(updatedHotel.getName());
            if(updatedHotel.getStars()>0&&updatedHotel.getStars()<=6){
                hotel.setStars(updatedHotel.getStars());
            }else{
                hotel.setStars(0);
            }
            hotel.setCity(updatedHotel.getCity());
            hotelRepository.update(hotel);

            return 1;
        }else{
            return -1;
        }
    }
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id")int id, @RequestBody Hotel updatedHotel){
        Hotel hotel = hotelRepository.getById(id);
        if(hotel!=null){
            if(hotel.getName()!=null) hotel.setName(hotel.getName());
            if(updatedHotel.getStars()>0&&updatedHotel.getStars()<=6){
                hotel.setStars(updatedHotel.getStars());
            }else{
                hotel.setStars(updatedHotel.getStars());
            }
            if(hotel.getCity()!=null) hotel.setCity(hotel.getCity());

            hotelRepository.update(hotel);
            return 1;
        }else{
            return -1;
        }
    }
}
