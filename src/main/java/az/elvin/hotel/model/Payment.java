package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "payment")
@Entity
@DynamicInsert
@ToString
public class Payment  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_payment;
    private Double amount;
    @OneToOne(targetEntity =PayType.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_type_id", referencedColumnName = "id_pay_type")
    private PayType payType;
    @Temporal(TemporalType.DATE)
    @Column(name = "added_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date added_date;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id_reservation")
    private Reservation reservation;
    private Double all_night_cost;
    private Double sum;
    private Double pending;
    private Double services_cost;
    private Double tax_cost;
    private Integer active;
    private Date data_date;

}

