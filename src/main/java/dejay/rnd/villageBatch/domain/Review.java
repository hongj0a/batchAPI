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
@Table(name = "review")
@Entity
@DynamicInsert
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "review_idx")
    private Long reviewIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "transactionIdx")
    private Transaction transaction;

    @ColumnDefault("0")
    @Column (name = "review_score")
    private Integer reviewScore;

    @Column (name = "review_content")
    @Lob
    private String reviewContent;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Column (name = "active_yn")
    @Builder.Default
    private boolean activeYn = true;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column (name = "owner_idx")
    private Long ownerIdx;

    @Column (name = "renter_idx")
    private Long renterIdx;

    @Column
    private String updator;

}
