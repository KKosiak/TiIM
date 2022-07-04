package pl.edu.wat.backend.entities;
import lombok.Data;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@Table(name = "Product")
public class ProductEntity {
    @Id
    private Long productID;
    private String name;
    private String price;
    private String amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="cat_categoryID", referencedColumnName="categoryID")
    private CategoryEntity cat;

}
