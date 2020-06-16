package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "floor")
@Entity
@DynamicInsert
@ToString
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_floor;
    private String name;
    private String floor_number;
    private String description;
    private Integer action;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List< Room > roomList;
}
