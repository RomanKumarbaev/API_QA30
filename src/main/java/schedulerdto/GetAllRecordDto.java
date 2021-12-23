package schedulerdto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class GetAllRecordDto {

    List<RecordDTO> records;
}
