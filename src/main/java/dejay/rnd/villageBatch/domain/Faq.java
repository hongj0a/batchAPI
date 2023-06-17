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
@Table(name = "faq")
@Entity
@DynamicInsert
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "faq_idx")
    private Long faqIdx;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "categoryIdx")
    private Category category;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "adminIdx")
    private Admin admin;

    @Column
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ColumnDefault("0")
    @Column (name = "delete_yn")
    private Boolean deleteYn;

    @Column (name = "active_yn")
    @Builder.Default
    private boolean activeYn = true;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column (name = "delete_at")
    private Date deleteAt;

    @Column (name = "active_at")
    private Date activeAt;

    @Column
    private String updator;

    @Column(columnDefinition = "LONGTEXT")
    private String answer;
}
