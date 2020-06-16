package az.elvin.hotel.dao;

import az.elvin.hotel.model.Amenities;
import az.elvin.hotel.model.Room;
import az.elvin.hotel.model.Room_Amenities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Room_AmenitiesDao extends CrudRepository<Room_Amenities,Long> {
    //@Query("select count(r.id_room_amenities) FROM Room_Amenities r inner join r.room bs  where r.active =1 AND bs.id_room = :id_room ")
  //  @Query("select r.id_room_amenities,r.room,r.amenities FROM Room_Amenities r  where r.active =1")
    List<Room_Amenities> findRoom_AmenitiesByRoom(Room room);
    Room_Amenities findRoom_AmenitiesByRoomAndAmenities(Room room, Amenities amenities);
}
