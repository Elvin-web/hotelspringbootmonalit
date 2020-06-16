package az.elvin.hotel.dao;

import az.elvin.hotel.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoomDao extends CrudRepository<Room,Long> {

    @Query("select count(r.id_room) from Room r where r.active =1")
    long roomAcount();

   // @Query("select r.id_room,r.number from Room r inner join r.roomStatus rs where r.active =1 and rs.id_room_status=1")
   @Query("select r.id_room,r.number from Room r  where r.active =1 ")
   // List<Room> findRoomByActiveAndRoomStatus();
   List<Room> findRoomByActive();
}
