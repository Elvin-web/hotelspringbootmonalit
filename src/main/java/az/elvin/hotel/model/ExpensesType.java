package az.elvin.hotel.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "expenses_type")
@Entity
@DynamicInsert
@ToString
public class ExpensesType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_expenses_type;
    private String expenses_type;
    private Integer active;
    private Date data_date;
    @OneToMany(mappedBy = "expensesType", cascade = CascadeType.ALL)
    private List< Expenses > expensesList;
}
