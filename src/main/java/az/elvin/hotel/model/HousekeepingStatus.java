package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "housekeeping_status")
@Entity
@DynamicInsert
@ToString
public class HousekeepingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_housekeeping_status;
    private String name;
    private String description;
    private Integer action;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "housekeepingStatus", cascade = CascadeType.ALL)
    private List< Housekeeping > housekeepingList;
}
