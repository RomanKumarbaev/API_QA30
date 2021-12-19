package dto;

import lombok.*;

//        "code": 0,
//        "details": "string",
//        "message": "string",
//        "timestamp": "2021-12-19T17:13:05.945Z"
//        }
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class ErrorDto {
    int code;
    String details;
    String message;
}
