package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "room_reservation")
@Entity
@DynamicInsert
@ToString
public class Room_Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room_reservation;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id_reservation")
    private Reservation reservation;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", referencedColumnName = "id_room")
    private Room room;
    private Integer active;
    private Date data_date;

}
