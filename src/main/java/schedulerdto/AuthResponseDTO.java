package schedulerdto;

import lombok.*;

//{
//        "registration": true,
//        "status": "string",
//        "token": "string"
//        }
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class AuthResponseDTO {
    boolean registration;
    String status;
    String token;

}
