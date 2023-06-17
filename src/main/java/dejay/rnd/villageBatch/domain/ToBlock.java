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
@Table(name = "to_block")
@Entity
@DynamicInsert
public class ToBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "to_block_idx")
    private Long toBlockIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx", referencedColumnName = "user_idx")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "blockUserIdx", referencedColumnName = "user_idx")
    private User blockUser;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;


    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Column (name = "delete_at")
    private Date deleteAt;


}
