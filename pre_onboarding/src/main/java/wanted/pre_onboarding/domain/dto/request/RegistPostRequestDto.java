package wanted.pre_onboarding.domain.dto.request;

import lombok.*;
import wanted.pre_onboarding.domain.entity.Company;
import wanted.pre_onboarding.domain.entity.Posting;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistPostRequestDto {
    private Long postingId;
    private Long companyId;
    private String postingNation; // 국가
    private String postingRegion; // 지역
    private String postingPosition; // 포지션
    private String postingBonus; // 보너스
    private String postingSkills; // 스킬
    private String postingDetail; // 상세 공고


    public Posting toPostingEntity(Company company) {
        return Posting.builder()
                .postingId(this.postingId)
                .company(company)
                .postingNation(this.postingNation)
                .postingRegion(this.postingRegion)
                .postingPosition(this.postingPosition)
                .postingBonus(this.postingBonus)
                .postingSkills(this.postingSkills)
                .postingDetail(this.postingDetail)
                .build();
    }
}
