package wanted.pre_onboarding.domain.service;

import wanted.pre_onboarding.domain.dto.request.ApplyRequestDto;

public interface ApplyService {

    // 6. 사용자는 채용공고에 지원합니다.
    void apply(ApplyRequestDto applyRequestDto);
}
