package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "services_reservation")
@Entity
@DynamicInsert
@ToString
public class Service_Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_services_reservation;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id_reservation")
    private Reservation reservation;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "services_id", referencedColumnName = "id_services")
    private Services services;
    private Integer active;
    private Date data_date;
}
