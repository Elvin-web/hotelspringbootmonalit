package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "room")
@Entity
@DynamicInsert
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room;
    private long number;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "floor_id", referencedColumnName = "id_floor")
    private Floor floor;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_status_id", referencedColumnName = "id_room_status")
    private RoomStatus roomStatus;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id_room_type")
    private RoomType roomType;
    private Integer active;
    private Date data_date;
   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "room_amenities",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id_room"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "id_amenities"))
    private List<Amenities> amenitiesList;*/

  /*  @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "room_amenities", joinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id_room") }, inverseJoinColumns = { @JoinColumn(name = "amenities_id" , referencedColumnName = "id_amenities") })
    private Set<Amenities> amenitiesSet = new HashSet<>();*/


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List< Room_Amenities > room_amenitiesList;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List< Housekeeping > housekeepingList;
}

