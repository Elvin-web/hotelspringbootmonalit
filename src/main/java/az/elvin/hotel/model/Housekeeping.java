package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "housekeeping")
@Entity
@DynamicInsert
@ToString
public class Housekeeping  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_housekeeping;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "housekeeping_status_id", referencedColumnName = "id_housekeeping_status")
    private HousekeepingStatus housekeepingStatus;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "staff_id", referencedColumnName = "id_staff")
    private Staff staff;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", referencedColumnName = "id_room")
    private Room room;
    @Temporal(TemporalType.DATE)
    @Column(name = "clean_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date clean_date;
    private String remark;
    private Integer active;
    private Date data_date;


}
