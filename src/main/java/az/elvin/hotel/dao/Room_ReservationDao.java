package az.elvin.hotel.dao;

import az.elvin.hotel.model.Reservation;
import az.elvin.hotel.model.Room_Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Temporal;
import java.util.List;

public interface Room_ReservationDao extends CrudRepository<Room_Reservation, Long> {
    // @Query(value = "select count(r.id_room_reservation) FROM Room_Reservation r inner join r.reservation rm inner join rm.bookingStatus bs where r.active =1 AND bs.id_booking_status =1 AND  (DATEDIFF(CURDATE(),r.data_date)=0 OR DATEDIFF(CURDATE(),r.data_date)=1 )")
    //@Query(value = "select count(r.id_room_reservation) FROM Room_Reservation r inner join r.reservation rm inner join rm.bookingStatus bs where r.active =1 AND bs.id_booking_status =1 AND  r.data_date between current_date and abs(current_date -1) ")
    //@Query(value = "select count(r.id_room_reservation) FROM Room_Reservation r inner join r.reservation rm inner join rm.bookingStatus bs where r.active =1 AND bs.id_booking_status =1 AND (r.data_date < CURRENT_DATE AND r.data_date > CURRENT_DATE)")
   // @Query(value = "select count(r.id_room_reservation) FROM Room_Reservation r where r.active =1  and (function('DATEDIFF',current_date,r.data_date)=0 or function('DATEDIFF',current_date,r.data_date)=1)  ")
    @Query(value = "select count(r.id_room_reservation) FROM Room_Reservation r where r.active =1  and function('DATEDIFF',current_date,r.data_date)=0 ")
    long reservationTodayAcount();

    List<Room_Reservation> findReservation_RoomByReservation(Reservation reservation);
}


/*FUNCTION('DATEDIFF', :hour ,'2013/05/05' , '2013/08/20')*/