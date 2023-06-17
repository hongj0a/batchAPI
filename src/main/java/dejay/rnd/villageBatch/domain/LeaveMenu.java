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
@Table(name = "leave_menu")
@Entity
@DynamicInsert
public class LeaveMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "leave_idx")
    private Long leaveIdx;

    @Column (name = "view_id")
    private String viewId;

    @Column (name = "view_name")
    private String viewName;

    @Column (name = "leave_cnt")
    private Long leaveCnt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;


}
