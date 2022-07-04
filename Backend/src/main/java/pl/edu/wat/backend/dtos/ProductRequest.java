package pl.edu.wat.backend.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest
{
    private Long productID;
    private String name;
    private String price;
    private String amount;
    private Long categoryID;
}

