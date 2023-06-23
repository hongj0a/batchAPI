package dejay.rnd.villageBatch.domain;

import jakarta.persistence.*;
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
@Table(name = "alarm")
@Entity
@DynamicInsert
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_idx")
    private Long alarmIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private User user;

    @ManyToOne
    @JoinColumn(name = "adminIdx")
    private Admin admin;

    @ColumnDefault("0")
    @Column(name = "read_yn")
    private boolean readYn;

    @Column(length = 50000)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "target_idx")
    private Long targetIdx;

    @Column(name = "target_idx2")
    private Long targetIdx2;

    @Column
    private Integer type;

    @Column(name = "host_idx")
    private Long hostIdx;

    @Column (name = "update_at")
    private Date updateAt;

    @Column(length = 50000)
    private String updator;
}
