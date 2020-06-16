package az.elvin.hotel.dao;
import az.elvin.hotel.model.Reservation;
import az.elvin.hotel.model.Service_Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface Service_ReservationDao extends CrudRepository<Service_Reservation, Long> {
    List<Service_Reservation> findService_ReservationsByReservation(Reservation reservation);
}
