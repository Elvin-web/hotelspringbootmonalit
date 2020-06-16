package az.elvin.hotel.dao;

import az.elvin.hotel.model.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionDao extends CrudRepository<Position, Long> {

   // Position findPositionById_position(Long id_position);
}
