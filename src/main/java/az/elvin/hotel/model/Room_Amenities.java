package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@DynamicInsert
@Entity
@ToString
@Table(name = "room_amenities")
public class Room_Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room_amenities;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", referencedColumnName = "id_room")
    private Room room;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "amenities_id", referencedColumnName = "id_amenities")
    private Amenities amenities;
    private Integer active;
    private Date data_date;
}
