package wanted.pre_onboarding.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllPostingResponseDto {
    private Long postingId;
    private String companyName;
    private String postingNation; // 국가
    private String postingRegion; // 지역
    private String postingPosition; // 포지션
    private String postingBonus; // 보너스
    private String postingSkills; // 스킬
}
