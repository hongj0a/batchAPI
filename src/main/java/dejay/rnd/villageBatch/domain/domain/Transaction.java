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
@Table(name = "transaction")
@Entity
@DynamicInsert
public class Transaction {
    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!
     * 오너가 렌탈매칭을 누를때 생성
     * 오너 20, 렌터 20
     * !!!!!!!!!!!!!!!!!!!!!!!!!!
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "transaction_idx")
    private Long transactionIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn (name = "rentalIdx")
    private Rental rental;

    //빌려간 사람의 idx
    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;

    @ColumnDefault("0")
    @Column (name = "cancel_yn")
    private boolean cancelYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "pay_at")
    private Date payAt;

    @Column (name = "cancel_at")
    private Date cancelAt;

    @Column (name = "return_at")
    private Date returnAt;

    /**
     *  * 렌탈오너 상태
     *      * 10 : 렌탈매칭
     *      * 30 : 렌탈취소 [cancel_yn(true)] , 렌탈중(renter 40,50 인 경우)
     *      * 60 : 이의신청, 렌탈완료
     *      * 70 : 렌탈완료
     */
    @Column (name = "owner_status")
    @ColumnDefault("10")
    private Integer ownerStatus;

    /**
     *  * 렌터 상태
     *      * 10 : 매칭대기중
     *      * 20 : 매칭완료, 매칭취소 [cancel_yn (true)]
     *      * 40 : 물품인수 (50), 렌탈중
     *      * 50 : 물품반납(60), 이의신청일 때 중재중
     *      * 70 : 렌탈완료
     */
    @Column (name = "renter_status")
    @ColumnDefault("20")
    private Integer renterStatus;

    @Column(length = 50000)
    private String updator;

    @Column (name = "transaction_num")
    private String transactionNum;

    @ColumnDefault("0")
    @Column (name = "return_yn")
    private boolean returnYn;

    @Column (name = "coupon_idx")
    private Integer couponIdx;

    @Column (length = 100, name = "mypay_fee")
    private String myPayFee;

    @Column (length = 100, name = "coupon_fee")
    private String couponFee;

    @Column (length = 100 , name = "final_pay_fee")
    private String finalPayFee;

    /**
     * 결제방식
     * 0 : 계좌이체
     * 1 : 카드결제
     * 2 : 무통장입금
     */
    @Column (length = 100 , name = "pay_method")
    private Integer payMethod;

    @Column (name = "complete_at")
    private Date completeAt;

    /**
     * renter 상태 업데이트 될 때 timestamp
     * 예외) owner 60일 때 timestamp
     */
    @Column (name = "status_at")
    private Date statusAt;
}
