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
@Table(name = "bell_schedule")
@Entity
@DynamicInsert
public class BellSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "bell_idx")
    private Long bellIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "rentalIdx")
    private Rental rental;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @ColumnDefault("0")
    @Column (name = "bell_yn")
    private boolean bellYn;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

}
