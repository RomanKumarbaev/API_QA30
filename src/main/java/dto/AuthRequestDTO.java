package dto;

//{
//        "email": "string",
//        "password": "string"
//        }

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class AuthRequestDTO {

    String email;
    String password;
}
