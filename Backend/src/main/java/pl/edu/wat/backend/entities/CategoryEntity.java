package pl.edu.wat.backend.entities;
import lombok.Data;
import javax.persistence.*;
import java.util.List;



@Data
@Entity
@Table(name = "Categories")
public class CategoryEntity {
    @Id
    private Long categoryID;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cat")
    private List<ProductEntity> products;

}
