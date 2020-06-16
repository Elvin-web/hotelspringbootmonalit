package az.elvin.hotel.dao;

import az.elvin.hotel.model.Price;
import az.elvin.hotel.model.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PriceDao extends CrudRepository<Price,Long> {

}
