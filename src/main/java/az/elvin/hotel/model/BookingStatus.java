package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "booking_status")
@Entity
@DynamicInsert
@ToString
public class BookingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_booking_status;
    private String booking_status;
    private Integer active;
    private Date data_date;
}
