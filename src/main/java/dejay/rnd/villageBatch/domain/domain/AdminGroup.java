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
@Table(name = "admin_group")
@Entity
@DynamicInsert
public class AdminGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "group_idx")
    private Long groupIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @Column(length = 50000)
    private String updator;

    @Column (name = "group_name", unique = true)
    @NotNull(message = " group name은 Null 일 수 없습니다. ")
    private String groupName;

    @Column (length = 5000, name ="group_desc")
    private String groupDesc;

    @ColumnDefault("1")
    @Column (name = "active_yn")
    private boolean activeYn;

    @ColumnDefault("0")
    @Column (name ="delete_yn")
    private boolean deleteYn;

    @NotNull
    @Column (name = "create_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "delete_at")
    private Date deleteAt;
}
