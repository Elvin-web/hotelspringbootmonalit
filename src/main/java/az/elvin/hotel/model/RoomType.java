package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "room_type")
@Entity
@DynamicInsert
@ToString
public class RoomType  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room_type;
    private String room_type;
    private String slug;
    private String short_code;
    private String description;
    @OneToOne(targetEntity =RoomStructure.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_structure_id", referencedColumnName = "id_room_structure")
    private RoomStructure roomStructure;
    @OneToOne(targetEntity =Price.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", referencedColumnName = "id_price")
    private Price price;
    private String image;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    private List< Room > roomList;

}
