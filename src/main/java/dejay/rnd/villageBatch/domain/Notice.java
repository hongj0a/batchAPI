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
@Table(name = "notice")
@Entity
@DynamicInsert
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "notice_idx")
    private Long noticeIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    /**
     * 공지사항 타입 고정값, 하드코딩
     * 0. 공지
     * 1. 이벤트
     * 2. 채용
     */
    @Column (name = "notice_type")
    private String noticeType;

    @Column(length = 5000)
    private String title;

    @Column(length = 50000)
    private String content;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private Boolean deleteYn;

    @Column (name = "active_yn")
    @Builder.Default
    private boolean activeYn = true;

    /**
     * 공지사항 메인노출
     * 10 : 일반
     * 20 : 시스템 (메인노출)
     * 30 : 기타... 나중에 추가될 수 있음
     */
    @ColumnDefault("10")
    @Column (name = "view_type")
    private String viewType;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column
    private String updator;

    @Column (name = "active_at")
    private Date activeAt;

    @Column (name = "start_at")
    private Date startAt;

    @Column (name = "end_at")
    private Date endAt;

    @ColumnDefault("0")
    @Column (name = "push_now_yn")
    private boolean pushNowYn;
}
