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
@Table(name = "rental_town_info")
@Entity
@DynamicInsert
public class RentalTownInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "rental_town_info_idx")
    private Long rentalTownInfoIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn (name = "rentalIdx")
    private Rental rental;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "townIdx")
    private Town town;

    @Column (name = "active_yn")
    @Builder.Default
    private boolean activeYn = true;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column
    private String updator;
}
