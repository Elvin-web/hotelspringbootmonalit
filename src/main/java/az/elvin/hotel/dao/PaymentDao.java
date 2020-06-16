package az.elvin.hotel.dao;

import az.elvin.hotel.model.Guest;
import az.elvin.hotel.model.Payment;
import az.elvin.hotel.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends CrudRepository<Payment, Long> {
    List<Payment> findPaymentByReservationGuest(Guest guest);
   @Query("select SUM(p.amount) FROM  Payment p  where p.active =1 and function('DATEDIFF',current_date,p.data_date)<8 ")
    Long ToWeekIncomeCount();

    Payment findPaymentByReservation(Reservation reservation);
}
/*AND (p.data_date > NOW()-INTERVAL 1 WEEK)*/
/* long findPaymentByData_dateAfter();*/
/*b.date_time < NOW() + INTERVAL 7 DAY*/