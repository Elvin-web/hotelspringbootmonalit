package az.elvin.hotel.dao;

import az.elvin.hotel.model.Tax;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaxDao extends CrudRepository<Tax,Long> {

}
