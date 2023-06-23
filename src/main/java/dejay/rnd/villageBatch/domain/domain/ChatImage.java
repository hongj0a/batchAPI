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
@Table(name = "chat_image")
@Entity
@DynamicInsert
public class ChatImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "image_idx")
    private Long imageIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "chatContentIdx")
    private ChatContent chatContent;

    @Column (name = "image_url")
    @Lob
    private String imageUrl;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

}
