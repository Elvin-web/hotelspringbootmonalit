package az.elvin.hotel.dao;

import az.elvin.hotel.model.PriceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PriceTypeDao extends CrudRepository<PriceType,Long> {

}
