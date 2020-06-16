package az.elvin.hotel.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "price_type")
@Entity
@DynamicInsert
public class PriceType  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_price_type;
    private String price_type;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "priceType", cascade = CascadeType.ALL)
    private List< Services > servicesList;
}
