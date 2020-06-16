package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "room_status")
@Entity
@DynamicInsert
@ToString
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room_status;
    private String room_status;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "roomStatus", cascade = CascadeType.ALL)
    private List< Room > roomList;
}
