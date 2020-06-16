package az.elvin.hotel.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "login_hotel")
@Entity
@DynamicInsert

public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_login_hotel;
    private String username;
    private String password;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id", referencedColumnName = "id_staff")
    private Staff staff;
    private Integer active;
    private Date data_date;
    private String token;
    @OneToOne(targetEntity =Role.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id_role")
    private Role role;
}



