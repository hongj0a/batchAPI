package dejay.rnd.villageBatch.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PushDto {
    private Long userIdx;
    private Long adminIdx;
    private Long[] hostIdxes;
    private String title;
    private String message;
    private Long targetIdx;
    private int type = 0;
    private String topicType;
}
