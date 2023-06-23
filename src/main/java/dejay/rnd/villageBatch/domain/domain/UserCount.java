package dejay.rnd.villageBatch.domain.domain;

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
@Table(name = "user_count")
@Entity
@DynamicInsert
public class UserCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "count_idx")
    private Long countIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;

    @ColumnDefault("0")
    @Column (name = "receive_review_cnt")
    private Long receiveReviewCnt;

    @ColumnDefault("0")
    @Column (name = "give_review_cnt")
    private Long giveReviewCnt;

    @ColumnDefault("0")
    @Column (name = "login_cnt")
    private Long loginCnt;

    @ColumnDefault("0")
    @Column (name = "cell_cnt")
    private Long cellCnt;

    @ColumnDefault("0")
    @Column (name = "buy_cnt")
    private Long buyCnt;

    @ColumnDefault("0")
    @Column (name = "block_user_cnt")
    private Long blockUserCnt;

    @ColumnDefault("0")
    @Column (name = "block_post_cnt")
    private Long blockPostCnt;

    @ColumnDefault("0")
    @Column (name = "block_review_cnt")
    private Long blockReviewCnt;

    @ColumnDefault("0")
    @Column (name = "view_cnt")
    private Long viewCnt;

    @ColumnDefault("0")
    @Column (name = "all_like_cnt")
    private Long allLikeCnt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

}
