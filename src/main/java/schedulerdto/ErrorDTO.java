package schedulerdto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class ErrorDTO {
    int code;
    String details;
    String message;
}
