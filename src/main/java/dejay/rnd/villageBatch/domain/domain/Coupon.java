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
@Table(name = "coupon")
@Entity
@DynamicInsert
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "coupon_idx")
    private Long couponIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(length = 5000, name = "coupon_desc")
    private String couponDesc;

    @Column (name = "open_date")
    private Date openDate;

    @Column (name = "start_date")
    private Date startDate;

    @Column (name = "end_date")
    private Date endDate;

    @Column (length = 100, name = "coupon_fee")
    private String couponFee;

    //웬만하면 쓰지말 것 리팩터링 때 다 지울 것
    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column(length = 50000)
    private String updator;

}
