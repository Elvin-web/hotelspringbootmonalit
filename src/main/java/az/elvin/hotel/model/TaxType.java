package az.elvin.hotel.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tax_type")
@DynamicInsert
public class TaxType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tax_type;
    private String type;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "taxType", cascade = CascadeType.ALL)
    private List< Tax > taxList;
}
