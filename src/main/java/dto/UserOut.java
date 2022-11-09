
package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor

public class UserOut {

    private Long Code;

    private String Message;

    private String Type;

    public UserOut() {}
}
