package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "room_structure")
@Entity
@DynamicInsert
@ToString
public class RoomStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room_structure;
    private Long base_occupancy;
    private Long higher_occupancy;
    private Long extra_bed;
    private Long kids_occupancy;
    private Integer active;
    private Date data_date;

}
