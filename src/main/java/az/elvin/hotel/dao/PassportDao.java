package az.elvin.hotel.dao;

import az.elvin.hotel.model.Passport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PassportDao extends CrudRepository<Passport,Long> {

}
