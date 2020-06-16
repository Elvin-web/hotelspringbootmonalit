package az.elvin.hotel.dao;

import az.elvin.hotel.model.TaxType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxTypeDao extends CrudRepository<TaxType, Long> {

}
