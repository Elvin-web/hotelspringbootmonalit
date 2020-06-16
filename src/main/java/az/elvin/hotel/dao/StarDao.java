package az.elvin.hotel.dao;

import az.elvin.hotel.model.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StarDao extends CrudRepository<Star,Long> {

}
