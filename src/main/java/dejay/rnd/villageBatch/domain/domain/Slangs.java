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
@Table(name = "slangs")
@Entity
@DynamicInsert
public class Slangs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "slangs_idx")
    private Long slangsIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @Column ( length = 1000 )
    private String slang;

    @Column (name = "active_yn")
    @ColumnDefault("1")
    @Builder.Default
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
