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
@Table(name = "chat_room")
@Entity
@DynamicInsert
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "chat_room_idx")
    private Long chatRoomIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "rentalIdx")
    private Rental rental;

    @ManyToOne
    @JoinColumn (name = "transactionIdx")
    private Transaction transaction;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "toIdx", referencedColumnName = "user_idx")
    private User toUser;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "fromIdx", referencedColumnName = "user_idx")
    private User fromUser;


    @Column(name = "last_chat_message")
    private String lastChatMessage;

    @ColumnDefault("0")
    @Column(name = "visible_to")
    private Long visibleTo;

    @ColumnDefault("1")
    @Column (name = "first_yn")
    private boolean firstYn;

    @ColumnDefault("0")
    @Column (name = "read_yn")
    private boolean readYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "exit_at")
    private Date exitAt;

    @Column
    private String updator;

}
