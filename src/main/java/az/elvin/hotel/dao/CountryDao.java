package az.elvin.hotel.dao;

import az.elvin.hotel.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountryDao extends CrudRepository<Country,Long> {

}
