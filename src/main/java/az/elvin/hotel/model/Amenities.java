package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.*;

@Data
@Table(name = "amenities")
@Entity
@DynamicInsert
@ToString
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_amenities;
    private String name;
    private String description;
    private Integer action;
    private String image;
    private Integer active;
    private Date data_date;
   /* @ManyToMany(mappedBy = "amenitiesList")
    private List<Room> roomList = new ArrayList<>();*/
  /* @ManyToMany(mappedBy = "amenitiesSet")
   private Set<Room> roomSet = new HashSet<>();*/

    @OneToMany(mappedBy = "amenities", cascade = CascadeType.ALL)
    private List< Room_Amenities > room_amenitiesList;
}
