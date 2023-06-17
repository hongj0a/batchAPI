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
@Table(name = "group_member")
@Entity
@DynamicInsert
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "group_member_idx")
    private Long groupMemberIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "groupIdx")
    private AdminGroup adminGroup;

    @Column (name = "member_idx")
    private Integer memberIdx;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column
    private String updator;
}
