package dejay.rnd.villageBatch.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "charge")
@Entity
@DynamicInsert
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "charge_idx")
    private Long chargeIdx;

    @ManyToOne
    @JoinColumn (name = "userIdx")
    private User user;

    @ManyToOne
    @JoinColumn (name = "paymentsInfoIdx")
    private PaymentsInfo paymentsInfo;

    @Column (name = "pay_fee")
    private String payFee;

    @Column (name = "bank_name")
    private String bankName;

    @Column (name = "bank_number")
    private String bankNumber;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "amount")
    private String amount;

    @Column (name = "method_id")
    private String methodId;

    @Column (name = "order_id")
    private String orderId;

    @Column (name = "payment_key")
    private String paymentKey;

}
