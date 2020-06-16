package az.elvin.hotel.dao;

import az.elvin.hotel.model.PaymentStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentStatusDao extends CrudRepository<PaymentStatus,Long> {

}
