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
@Table(name = "rental_category_info")
@Entity
@DynamicInsert
public class RentalCategoryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "rental_category_info_idx")
    private Long rentalCategoryInfoIdx;

    @ManyToOne (fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn (name = "rentalIdx")
    private Rental rental;

    @ManyToOne
    @NotNull
    @JoinColumn (name = "categoryIdx")
    private Category category;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "create_at")
    private Date createAt;

    @Column (name = "update_at")
    private Date updateAt;

    @Column(length = 50000)
    private String updator;

}
