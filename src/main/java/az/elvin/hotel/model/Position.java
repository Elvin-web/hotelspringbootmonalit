package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "position")
@Entity
@DynamicInsert
@ToString
    public class Position  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_position")
    private Long id_position;
    private String position_value;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private List< Staff > staffList;
}
