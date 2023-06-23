package dejay.rnd.villageBatch.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arbitration_management")
@Entity
@DynamicInsert
public class ArbitrationManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "am_idx")
    private Long amIdx;

    @ManyToOne
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "transactionIdx")
    private Transaction transaction;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;


    // 중재상태 0:중재신청 1:중재중 2:중재완료
    // 관리자가 답변을 남기고 노출을 시키는 시점에 완료로 변경
    @ColumnDefault("0")
    @Column (name = "am_status")
    private Integer amStatus;

    @Column (name = "answer_content")
    private String answerContent;

    @Column (name = "am_content")
    private String amContent;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    //status 바뀌는 시점에 timestamp.
    private Date updateAt;

    @Column(length = 50000)
    private String updator;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column (name = "complete_at")
    private Date completeAt;

}
