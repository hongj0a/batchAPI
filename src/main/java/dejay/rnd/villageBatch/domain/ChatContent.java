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
@Table(name = "chat_content")
@Entity
@DynamicInsert
public class ChatContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "chat_idx")
    private Long chatIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "chatRoomIdx")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn (name = "userIdx")
    private User user;

    @ColumnDefault("0")
    @Column (name = "image_yn")
    private boolean imageYn;

    @ManyToOne
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @Column(length = 50000)
    private String content;

    @ColumnDefault("0")
    @Column (name = "check_yn")
    private boolean checkYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @ColumnDefault("0")
    @Column (name = "system_yn")
    private boolean systemYn;

}
