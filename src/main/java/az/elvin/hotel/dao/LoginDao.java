package az.elvin.hotel.dao;

import az.elvin.hotel.model.LoginUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends CrudRepository<LoginUser, Long> {


  LoginUser findLoginUserByUsernameAndPassword(String username,String password);

}

