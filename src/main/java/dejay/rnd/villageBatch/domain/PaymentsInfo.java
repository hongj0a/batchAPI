package dejay.rnd.villageBatch.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments_info")
@Entity
@DynamicInsert
public class PaymentsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "payments_info_idx")
    private Long paymentsInfoIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "transactionIdx")
    private Transaction transaction;

    @Column (name = "pay_request_date")
    private Date payRequestDate;

    @Column (name = "pay_finished_date")
    private Date payFinishedDate;

    @Column (name = "update_at")
    private Date updateAt;

    @Column
    private String updator;

    @Column (length = 100, name = "renter_price")
    private Integer renterPrice;

    @Column (length = 100, name = "rental_owner_price")
    private Integer rentalOwnerPrice;

    @Column
    @Lob
    private String reason;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

}
