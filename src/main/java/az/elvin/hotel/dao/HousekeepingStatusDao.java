package az.elvin.hotel.dao;

import az.elvin.hotel.model.HousekeepingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HousekeepingStatusDao extends CrudRepository<HousekeepingStatus,Long> {

}
