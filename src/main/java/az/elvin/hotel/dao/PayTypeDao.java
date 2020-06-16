package az.elvin.hotel.dao;

import az.elvin.hotel.model.PayType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PayTypeDao extends CrudRepository<PayType,Long> {

}
