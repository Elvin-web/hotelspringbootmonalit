package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "services")
@Entity
@DynamicInsert
@ToString
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_services;
    private String name;
    private Double amount;
    private Integer action;
    private String description;
    private Integer active;
    private Date data_date;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "price_type_id", referencedColumnName = "id_price_type")
    private PriceType priceType;

}
