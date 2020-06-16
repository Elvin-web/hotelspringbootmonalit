package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "price")
@Entity
@DynamicInsert
@ToString
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_price;
    private Double extra_bed_price;
    private Double base_price;
    private Integer active;
    private Date data_date;
}
