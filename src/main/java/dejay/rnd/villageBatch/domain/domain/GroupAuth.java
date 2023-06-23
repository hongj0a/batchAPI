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
@Table(name = "group_auth")
@Entity
@DynamicInsert
public class GroupAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "group_auth_idx")
    private Long groupAuthIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "groupIdx")
    private AdminGroup adminGroup;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "crudIdx")
    private CrudAuth crudAuth;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "createAt")
    private Date create_at;

    @Column (name = "update_at")
    private Date updateAt;

    @Column(length = 50000)
    private String updator;

    @Column (name = "deleteAt")
    private Date delete_at;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;
}
