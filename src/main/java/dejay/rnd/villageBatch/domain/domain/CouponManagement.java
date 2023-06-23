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
@Table(name = "coupon_management")
@Entity
@DynamicInsert
public class CouponManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "coupon_manage_idx")
    private Long couponManageIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "couponIdx")
    private Coupon coupon;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @ColumnDefault("0")
    @Column (name = "use_yn")
    private boolean useYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column (name = "use_at")
    private Date useAt;

    @Column(length = 50000)
    private String updator;

}
