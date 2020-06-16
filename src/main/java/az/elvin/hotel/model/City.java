package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "city")
@Entity
@DynamicInsert
@ToString
public class City  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_city;
    private String name;
    private Integer active;
    private Date data_date;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "country_id", referencedColumnName = "id_country")
    private Country country;
}
