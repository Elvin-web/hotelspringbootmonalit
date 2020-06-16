package az.elvin.hotel.dao;

import az.elvin.hotel.model.Housekeeping;
import az.elvin.hotel.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HousekeepingDao extends CrudRepository<Housekeeping,Long> {

    List<Housekeeping> findHousekeepingByRoom(Room room);
}
