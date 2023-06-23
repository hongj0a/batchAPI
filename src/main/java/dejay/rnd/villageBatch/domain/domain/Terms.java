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
@Table(name = "terms")
@Entity
@DynamicInsert
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "terms_idx")
    private Long termsIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    /**
     * 약관 분류값
     * 0 : 개인정보처리방침
     * 1 : 이용약관
     * 2 : 마케팅동의
     * 3 : 제3자정보제공
     * 4 : 위치기반
     * ...
     */
    @Column
    private Integer type;

    @Column(length = 5000)
    private String title;

    @Column(length = 50000)
    private String content;

    @Column
    private String version;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @Column (name = "major_changes", length = 5000)
    private String majorChanges;

    @Column(length = 50000)
    private String updator;

    @Column (name = "reservation_date")
    private Date reservationDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

}
