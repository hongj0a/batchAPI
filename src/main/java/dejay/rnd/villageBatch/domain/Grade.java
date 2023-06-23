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
@Table(name = "grade")
@Entity
@DynamicInsert
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_idx")
    private Long gradeIdx;

    @Column(name = "grade_name")
    private String gradeName;

    @Column(name = "middle_grade")
    private Long middleGrade;

    @Column(name = "grade_score")
    private Long gradeScore;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "createAt")
    private Date create_at;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(length = 50000)
    private String updator;

    @Column(name = "active_yn")
    @Builder.Default
    private boolean activeYn = true;

    @Column(name = "admin_idx")
    private Long adminIdx;

    @Column(length = 1000)
    private String imageUrl;

    @Column (name = "menu_num")
    private Integer menuNum;

}
