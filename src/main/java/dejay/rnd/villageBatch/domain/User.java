package dejay.rnd.villageBatch.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
@DynamicInsert
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_idx")
    private Long userIdx;

    @Column (name = "nick_name")
    private String nickName;

    @Column (name = "id_email")
    private String idEmail;

    @ColumnDefault("test")
    @Column (name = "email", unique = true)
    private String email;

    @Column (name = "phone_num")
    private String phoneNum;

    @Column(length = 1000, name = "profile_image_url")
    private String profileImageUrl;

    @ColumnDefault("1")
    @Column (name = "user_level")
    private Long userLevel;

    @ColumnDefault("0")
    @Column (name = "activity_score")
    private Integer activityScore;

    @Column (name = "sns_type")
    private String snsType;

    @Column (name = "sns_name")
    private String snsName;

    //long text
    @Lob
    @Column (name = "refresh_token")
    private String refreshToken;

    @Lob
    @Column (name = "push_token")
    private String pushToken;


    /**
     * 회원상태값
     * 10 : 활동회원
     * 20 : 휴면회원
     * 30 : 탈퇴회원
     * 40 : 블랙리스트 회원
     */
    @ColumnDefault("10")
    @Column (name = "status")
    private Integer status;

    /*@Builder.Default
    @Column (name = "status")
    private Integer status = 10;*/

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "levelup_at")
    private Date levelupAt;

    @ColumnDefault("0")
    @Column (name = "star_point")
    private String starPoint;

    @ColumnDefault("0")
    @Column (name = "do_not_disturb_time_yn")
    private boolean doNotDisturbTimeYn;

    @ColumnDefault("0")
    @Column (name = "chat_notice_yn")
    private boolean chatNoticeYn;

    @ColumnDefault("0")
    @Column (name = "activity_notice_yn")
    private boolean activityNoticeYn;

    @ColumnDefault("0")
    @Column (name = "marketing_notice_yn")
    private boolean marketingNoticeYn;

    @ColumnDefault("0")
    @Column (name = "notice_notice_yn")
    private boolean noticeNoticeYn;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "last_login_date")
    private Date lastLoginDate;

    @Column(length = 1000, name = "ci_value", unique = true)
    private String ciValue;

    @Column (name = "invitation_code")
    private String invitationCode;

    @ColumnDefault("0")
    @Column (name = "billy_pay")
    private String billyPay;

    @Column (name = "device_id")
    private String deviceId;

    //회원탈퇴시, 탈퇴사유idx
    @ManyToOne
    @JoinColumn (name = "categoryIdx")
    private Category category;

    @Column (name = "out_reason", length = 1000)
    private String outReason;

    @Column (name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_grade",
            joinColumns = {@JoinColumn(name = "user_idx", referencedColumnName = "user_idx")},
            inverseJoinColumns = {@JoinColumn(name = "grade_idx", referencedColumnName = "grade_idx")})
    private Set<Grade> grades;

    @Column(name = "lead_town")
    private Long leadTown;

    @Column(name = "town_1")
    private Long town1;

    @Column(name = "town_2")
    private Long town2;

    @Column(name = "town_3")
    private Long town3;

    @Column(name = "town_4")
    private Long town4;

    @Column (name = "active_at")
    private Date activeAt;

    @Column (name = "dormancy_at")
    private Date dormancyAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column (name = "black_at")
    private Date blackAt;

    @Column (name = "is_after_noon")
    private Boolean isAfterNoon;

    @Column (name = "do_not_disturb_start_hour")
    private Integer doNotDisturbStartHour;

    @Column (name = "do_not_disturb_end_hour")
    private Integer doNotDisturbEndHour;

    @Column (name = "do_not_disturb_start_minute")
    private Integer doNotDisturbStartMinute;

    @Column (name = "do_not_disturb_end_minute")
    private Integer doNotDisturbEndMinute;

    @Column
    private String updator;


}
