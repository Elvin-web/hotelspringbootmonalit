package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Data
@Table(name = "hotel")
@Entity
@DynamicInsert
@ToString
public class Hotel  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_hotel;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String logo;
    private Integer active;
    private Date data_date;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "star_id", referencedColumnName = "id_star")
    private Star star;

}


