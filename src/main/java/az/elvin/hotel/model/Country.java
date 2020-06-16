package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "country")
@Entity
@DynamicInsert
@ToString
public class Country  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_country;
    private  String name;
    private String sort_name;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List< City > cityList;
}
