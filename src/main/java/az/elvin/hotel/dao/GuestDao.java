package az.elvin.hotel.dao;

import az.elvin.hotel.model.AdvancedSearch;
import az.elvin.hotel.model.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GuestDao extends CrudRepository<Guest,Long> {


}
