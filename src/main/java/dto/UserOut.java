
package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class UserOut {

    private Long code;

    private String message;

    private String type;

    public UserOut() {}
}
