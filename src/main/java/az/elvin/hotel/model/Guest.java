package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "guest")
@Entity
@DynamicInsert
@ToString
public class Guest  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_guest;
    private String name;
    private String surname;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", referencedColumnName = "id_city")
    private City city;
    private String phone;
    private String gender;
    private String email;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "passport_id", referencedColumnName = "id_passport")
    private Passport passport;
    private String passport_number;
    private Date dob;
    private String image1;
    private String image2;
    private Integer active;
    private Date data_date;

}


