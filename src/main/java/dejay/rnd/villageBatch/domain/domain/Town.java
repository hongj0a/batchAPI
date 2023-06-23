package dejay.rnd.villageBatch.domain.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "town")
@Entity
@DynamicInsert
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "town_idx")
    private Long townIdx;

    @Column (name = "town_name")
    private String townName;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @ColumnDefault("0")
    @Column (name = "auth_status")
    private boolean authStatus;

    @ColumnDefault("0")
    @Column (name = "auth_count")
    private Integer authCount;

    @Column( precision = 18, scale = 10 , name = "region_latitue")
    private BigDecimal regionLatitue;

    @Column( precision = 18, scale = 10 , name = "region_longitude")
    private BigDecimal regionLongitude;

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
