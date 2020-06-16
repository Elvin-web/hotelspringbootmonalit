package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "pay_type")
@Entity
@DynamicInsert
@ToString
public class PayType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pay_type;
    private String pay_type;
    private Integer active;
    private Date data_date;

}

