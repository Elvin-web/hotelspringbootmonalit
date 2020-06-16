package az.elvin.hotel.dao;

import az.elvin.hotel.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReservationDao extends CrudRepository<Reservation, Long> {

    @Query("select count(r.id_reservation) FROM Reservation r inner join r.bookingStatus bs inner join r.paymentStatus ps where r.active =:active AND bs.id_booking_status = :id_booking_status AND ps.id_payment_status =:id_payment_status ")
    long countReservationByActiveAndBookingStatusAndPaymentStatus(Integer active, Long id_booking_status, Long id_payment_status);

    @Query("select count(r.id_reservation) FROM Reservation r inner join r.bookingStatus bs  where r.active =:active AND bs.id_booking_status = :id_booking_status ")
    long countReservationByActiveAndBookingStatus(Integer active, Long id_booking_status);
}
