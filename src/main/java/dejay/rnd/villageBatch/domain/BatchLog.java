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
@Table(name = "batch_log")
@Entity
@DynamicInsert
public class BatchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "log_idx")
    private Long logIdx;

    @Column
    private String method;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;


}
