package az.elvin.hotel.dao;

import az.elvin.hotel.model.Floor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FloorDao extends CrudRepository<Floor,Long> {
    @Query("select count(f.id_floor) FROM Floor f  where f.active =1")
    long countFloor();
}
