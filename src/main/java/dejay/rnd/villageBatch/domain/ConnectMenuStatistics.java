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
@Table(name = "connect_menu_stattistics")
@Entity
@DynamicInsert
public class ConnectMenuStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cms_idx")
    private Long cmsIdx;

    @ManyToOne
    @JoinColumn (name = "connectMenuIdx")
    private ConnectMenu connectMenu;

    @ManyToOne
    @JoinColumn (name = "leaveMenuIdx")
    private LeaveMenu leaveMenu;

    @Column (name = "today_cnt")
    private Long todayCnt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;


}
