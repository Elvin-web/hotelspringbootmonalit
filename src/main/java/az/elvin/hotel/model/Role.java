package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "role")
@Entity
@DynamicInsert
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;
    private String role_name;
    private Integer active;
    private Date data_date;

}
