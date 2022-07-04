package pl.edu.wat.backend.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryRequest
{
    private Long categoryID;
    private String name;
}
