package pl.edu.wat.backend.dtos;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse
{
    private Long productID;
    private String name;
    private String price;
    private String amount;
    private Long categoryID;

}
