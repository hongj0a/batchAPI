package dejay.rnd.villageBatch.domain.domain;

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
@Table(name = "affiliated_team")
@Entity
@DynamicInsert
public class AffiliatedTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "team_idx")
    private Long teamIdx;

    @ManyToOne
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @Column (name = "name")
    private String name;

    @Builder.Default
    @Column (name = "active_yn")
    private boolean activeYn = true;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column(length = 50000)
    private String updator;

}
