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
@Table(name = "my_attention")
@Entity
@DynamicInsert
public class MyAttention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "attention_idx")
    private Long attentionIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "userIdx")
    private User user;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "categoryIdx")
    private Category category;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column(length = 50000)
    private String updator;

}
