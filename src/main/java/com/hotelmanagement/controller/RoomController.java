package com.hotelmanagement.controller;

import com.hotelmanagement.entity.Room;
import com.hotelmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @GetMapping("")
    public List<Room> getAll(){
        return roomRepository.getAll();
    }

    @GetMapping("/{id}")
    public Room getById(@PathVariable("id") int id){
        return roomRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Room> rooms){
        return roomRepository.save(rooms);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id")int id, @RequestBody Room updatedRoom){
        Room room = roomRepository.getById(id);
        if(room!=null){
            room.setRoom_number(updatedRoom.getRoom_number());
            room.setRoom_type_id(updatedRoom.getRoom_type_id());
            room.setHotel_id(updatedRoom.getHotel_id());

            roomRepository.update(room);
            return 1;
        }else{
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int updatePartially(@PathVariable("id")int id, @RequestBody Room updatedRoom){
        Room room = roomRepository.getById(id);
        if(room!=null){
            if(updatedRoom.getRoom_number()!=0){updatedRoom.setRoom_number(room.getRoom_number());}
            if(updatedRoom.getRoom_type_id()!=0){updatedRoom.setRoom_type_id(room.getRoom_type_id());}
            if(updatedRoom.getHotel_id()!=0){updatedRoom.setHotel_id(room.getHotel_id());}

            roomRepository.update(room);
            return 1;
        }else{
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id")int id){
        return roomRepository.delete(id);
    }
}
