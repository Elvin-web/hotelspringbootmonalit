package az.elvin.hotel.dao;

import az.elvin.hotel.model.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomTypeDao extends CrudRepository<RoomType,Long> {
    @Query("select count(r.id_room_type) FROM RoomType r  where r.active =1")
    long roomTypeAcount();
}
