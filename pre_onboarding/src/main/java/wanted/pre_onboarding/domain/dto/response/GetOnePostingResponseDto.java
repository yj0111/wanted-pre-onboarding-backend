package wanted.pre_onboarding.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetOnePostingResponseDto {
    private Long postingId;
    private String companyName;
    private String postingNation; // 국가
    private String postingRegion; // 지역
    private String postingPosition; // 포지션
    private String postingBonus; // 보너스
    private String postingSkills; // 스킬
    private String postingDetail; // 채용 내용
    private List<Long> otherPostingList; // 회사가 올린 다른 채용공고 ID
}
