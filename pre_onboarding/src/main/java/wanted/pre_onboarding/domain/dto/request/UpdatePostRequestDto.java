package wanted.pre_onboarding.domain.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequestDto {
    private String postingNation; // 국가
    private String postingRegion; // 지역
    private String postingPosition; // 포지션
    private String postingBonus; // 보너스
    private String postingSkills; // 스킬
    private String postingDetail; // 상세 공고
}
