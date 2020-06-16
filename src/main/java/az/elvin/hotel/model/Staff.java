package az.elvin.hotel.model;

    import lombok.Data;
    import lombok.ToString;
    import org.hibernate.annotations.DynamicInsert;

    import javax.persistence.*;
    import java.util.Date;
    import java.util.List;

@Data
@Table(name = "staff")
@Entity
@DynamicInsert
@ToString
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_staff;
    @OneToOne(targetEntity =Hotel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id_hotel")
    private Hotel hotel;
    private String first_name;
    private String last_name;
    private String address;
    private Date dob;
    private String phone;
    private Double salary;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "position_id", referencedColumnName = "id_position")
    private Position position;
    private String job_start;
    private String job_end;
    private String gender;
    private String picture;
    private String email;
    private String username;
    private String password;
    private String ID;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List< Housekeeping > housekeepingList;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List< LoginUser > loginUserList;

}

