package az.elvin.hotel.dao;

import az.elvin.hotel.model.BookingStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingStatusDao extends CrudRepository<BookingStatus,Long> {

}
