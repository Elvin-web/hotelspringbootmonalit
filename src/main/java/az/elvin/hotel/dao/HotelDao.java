package az.elvin.hotel.dao;

import az.elvin.hotel.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelDao extends CrudRepository<Hotel,Long> {

}
