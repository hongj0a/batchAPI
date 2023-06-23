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
@Table(name = "status_history")
@Entity
@DynamicInsert
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "status_idx")
    private Long statusIdx;

    @ManyToOne
    @JoinColumn (name = "userIdx")
    private User user;

    @ManyToOne
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    /**
     * 회원상태값
     * 10 : 활동회원
     * 20 : 휴면회원
     * 30 : 탈퇴회원
     * 40 : 블랙리스트 회원
     */
    @Column (name = "active_at")
    private Date activeAt;

    @Column (name = "dormancy_at")
    private Date dormancyAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column (name = "black_at")
    private Date blackAt;

    @Column(length = 1000)
    private String title;

    @Column(length = 5000)
    private String content;

    @Column
    private Integer status;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;
}
