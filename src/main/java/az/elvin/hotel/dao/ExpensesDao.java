package az.elvin.hotel.dao;

import az.elvin.hotel.model.Expenses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpensesDao extends CrudRepository<Expenses,Long> {

}
