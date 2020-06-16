package az.elvin.hotel.dao;

import az.elvin.hotel.model.RoomStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomStatusDao extends CrudRepository<RoomStatus,Long> {

}
