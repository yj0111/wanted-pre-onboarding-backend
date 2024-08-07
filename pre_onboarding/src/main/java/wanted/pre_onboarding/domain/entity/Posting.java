package wanted.pre_onboarding.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String postingNation; // 국가

    @Column(nullable = false)
    private String postingRegion; // 지역

    @Column(nullable = false)
    private String postingPosition; // 포지션

    @Column(nullable = false)
    private String postingBonus; // 보너스

    @Column(nullable = false)
    private String postingSkills; // 스킬

    @Column(nullable = false)
    private String postingDetail; // 상세 공고

}
