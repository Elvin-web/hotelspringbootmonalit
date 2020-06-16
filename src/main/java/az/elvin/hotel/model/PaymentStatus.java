package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "payment_status")
@Entity
@DynamicInsert
@ToString
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_payment_status;
    private String payment_status;
    private Integer active;
    private Date data_date;
}
