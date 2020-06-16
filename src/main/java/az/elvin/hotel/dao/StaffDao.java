package az.elvin.hotel.dao;

import az.elvin.hotel.model.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StaffDao extends CrudRepository<Staff,Long> {

}
