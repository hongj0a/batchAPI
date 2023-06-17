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
@Table(name = "statistics")
@Entity
@DynamicInsert
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "statistics_idx")
    private Long statisticsIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;

    @ColumnDefault("0")
    @Column (name = "today_connector_cnt")
    private Long todayConnectorCnt;

    @ColumnDefault("0")
    @Column (name = "yesterday_connector_cnt")
    private Long yesterdayConnectorCnt;

    @ColumnDefault("0")
    @Column (name = "continuity_connector_cnt")
    private Long continuityConnectorCnt;

    @ColumnDefault("0")
    @Column (name = "total_join_cnt")
    private Long totalJoinCnt;

    @ColumnDefault("0")
    @Column (name = "total_active_cnt")
    private Long totalActiveCnt;

    @ColumnDefault("0")
    @Column (name = "total_dormancy_cnt")
    private Long totalDormancyCnt;

    @ColumnDefault("0")
    @Column (name = "total_out_member_cnt")
    private Long totalOutMemberCnt;

    @ColumnDefault("0")
    @Column (name = "total_rental_cnt")
    private Long totalRentalCnt;

    @ColumnDefault("0")
    @Column (name = "active_rental_cnt")
    private Long activeRentalCnt;

    @ColumnDefault("0")
    @Column (name = "delete_renteal_cnt")
    private Long deleteRentalCnt;

    @ColumnDefault("0")
    @Column (name = "possible_rental_cnt")
    private Long possibleRentalCnt;

    @ColumnDefault("0")
    @Column (name = "impossible_rental_cnt")
    private Long impossibleRentalCnt;

    @ColumnDefault("0")
    @Column (name = "hide_rental_cnt")
    private Long hideRentalCnt;

    @ColumnDefault("0")
    @Column (name = "total_review_cnt")
    private Long totalReviewCnt;

    @ColumnDefault("0")
    @Column (name = "active_review_cnt")
    private Long activeReviewCnt;

    @ColumnDefault("0")
    @Column (name = "inactive_review_cnt")
    private Long inactiveReviewCnt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

}
