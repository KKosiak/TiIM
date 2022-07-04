package pl.edu.wat.backend.dtos;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse
{
    private Long categoryID;
    private String name;
}
