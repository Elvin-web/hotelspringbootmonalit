package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "star")
@Entity
@DynamicInsert
@ToString
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_star;
    private long star;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "star", cascade = CascadeType.ALL)
    private List< Hotel > hotelList;
}
