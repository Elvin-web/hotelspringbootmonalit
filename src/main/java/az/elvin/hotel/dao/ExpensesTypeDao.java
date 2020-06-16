package az.elvin.hotel.dao;

import az.elvin.hotel.model.ExpensesType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesTypeDao  extends CrudRepository<ExpensesType,Long> {
}
