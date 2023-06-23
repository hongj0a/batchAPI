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
@Table(name = "block_post")
@Entity
@DynamicInsert
public class BlockPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "block_idx")
    private Long blockIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "rentalIdx")
    private Rental rental;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "categoryIdx")
    private Category category;

    @Column
    private String reason;

    @Column (name = "reporter_idx")
    private Long reporterIdx;

    @ColumnDefault("0")
    @Column (name = "processing_status")
    private Integer processingStatus;

    @Column (name = "processing_content", length = 50000)
    private String processingContent;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column(length = 50000)
    private String updator;

    @Column (name = "complete_at")
    private Date completeAt;

}
