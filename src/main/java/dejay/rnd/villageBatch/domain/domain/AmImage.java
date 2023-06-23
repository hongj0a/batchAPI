package dejay.rnd.villageBatch.domain.domain;

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
@Table(name = "am_image")
@Entity
@DynamicInsert
public class AmImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "image_idx")
    private Long imageIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "amIdx")
    private ArbitrationManagement arbitrationManagement;

    @Column (name = "image_url")
    @Lob
    private String imageUrl;

    @Column(length = 50000)
    private String updator;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    //status 바뀌는 시점에 timestamp.
    private Date updateAt;

}
