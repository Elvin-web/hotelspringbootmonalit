package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "expenses")
@Entity
@DynamicInsert
@ToString
public class Expenses  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_expenses;
    private String name;
    private Double amount;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_insert")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_insert;
    private String document;
    private Integer active;
    private Date data_date;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "expenses_type_id", referencedColumnName = "id_expenses_type")
    private ExpensesType expensesType;
}
