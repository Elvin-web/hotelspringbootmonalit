package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Table(name = "tax")
@Entity
@DynamicInsert
@ToString
public class Tax  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tax;
    private String name;
    private String code;
    private Double rate;
    private Integer active;
    private Date data_date;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tax_type_id", referencedColumnName = "id_tax_type")
    private TaxType taxType;
}
