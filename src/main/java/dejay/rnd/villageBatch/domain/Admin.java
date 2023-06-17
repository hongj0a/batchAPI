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
@Table(name = "admin")
@Entity
@DynamicInsert
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_idx")
    private Long adminIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "teamIdx")
    private AffiliatedTeam affiliatedTeam;

    @Column
    @NotNull(message = " admin id는 Null 일 수 없습니다.")
    private String id;

    @Column (length = 1000)
    @NotNull(message = " admin password는 Null 일 수 없습니다. ")
    private String password;

    @ColumnDefault("1")
    @Column (name = "active_yn")
    private boolean activeYn;

    @Column
    @NotNull
    private String name;

    @Column (name = "nick_name")
    private String nickName;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private boolean deleteYn;

    @NotNull
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name ="last_login_date")
    private Date lastLoginDate;

    @Column (name = "ip_value")
    @Lob
    //접속허용 ip 관리 필드
    private String ipValue;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column
    private String updator;

    @Column (name = "certification_number")
    private String certificationNumber;

    @Column (name = "certification_yn")
    @ColumnDefault("0")
    private boolean certificationYn;

    //long text
    @Lob
    @Column (name = "access_token")
    private String accessToken;

    @Column (name = "approval_date")
    private Date approvalDate;

    @Column (name = "phone_num")
    private String phoneNum;

}
