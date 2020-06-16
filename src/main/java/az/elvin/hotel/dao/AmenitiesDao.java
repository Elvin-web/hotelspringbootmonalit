package az.elvin.hotel.dao;

import az.elvin.hotel.model.Amenities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AmenitiesDao extends CrudRepository<Amenities,Long> {

}
