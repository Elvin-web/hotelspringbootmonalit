package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "reservation")
@Entity
@DynamicInsert
@ToString
public class Reservation  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservation;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "guest_id", referencedColumnName = "id_guest")
    private Guest guest;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id_room_type")
    private RoomType roomType;
    private Long adults;
    private Long children;
    @Temporal(TemporalType.DATE)
    @Column(name = "check_in")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date check_in;
    @Temporal(TemporalType.DATE)
    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date check_out;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "booking_status_id", referencedColumnName = "id_booking_status")
    private BookingStatus bookingStatus;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id_hotel")
    private Hotel hotel;
    private Long night;
    private Integer extra_bed;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_status_id", referencedColumnName = "id_payment_status")
    private PaymentStatus paymentStatus;
    private Integer active;
    private Date data_date;



}

