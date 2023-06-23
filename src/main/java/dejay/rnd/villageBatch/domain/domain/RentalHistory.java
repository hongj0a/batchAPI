package dejay.rnd.villageBatch.domain.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental_history")
@Entity
@DynamicInsert
public class RentalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "history_idx")
    private Long historyIdx;

    @Column (name = "user_idx")
    private Long userIdx;

    @Column (name = "rental_idx")
    private Long rentalIdx;

    @Column (name = "renter_name")
    private String renterName;

    /**
     * 1 : 렌탈가능
     * 2 : 렌탈중
     * 3 : 렌탈완료
     * 4 : 렌탈숨기기
     */
    private int status;

    @Column
    private String title;

    private Integer deposit;

    @Column (name = "rental_price")
    private Integer rentalPrice;

    @Column(length = 50000)
    private String content;

    @Column (name = "trading_method")
    private Integer tradingMethod;

    @Column (name = "like_cnt")
    private Integer likeCnt;

    @Column (name = "view_cnt")
    private Integer viewCnt;

    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "complete_at")
    private Date completeAt;

    @Column (name = "active_yn")
    private boolean activeYn;

    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column(length = 50000)
    private String updator;

    @Column(name ="pull_up_at")
    private Date pullUpAt;

    @Column(name ="pull_up_cnt")
    private Integer pullUpCnt;

    @Column(name = "lead_town")
    private Integer leadTown;

    @Column(name = "town_1")
    private Integer town1;

    @Column(name = "town_2")
    private Integer town2;

    @Column(name = "town_3")
    private Integer town3;

    @Column(name = "town_4")
    private Integer town4;

    @Column(length = 5000)
    private String memo;
}
