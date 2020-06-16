package az.elvin.hotel.dao;

import az.elvin.hotel.model.Services;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ServicesDao extends CrudRepository<Services, Long> {

}
