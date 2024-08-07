package wanted.pre_onboarding.domain.service;

import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;
import wanted.pre_onboarding.domain.dto.response.GetAllPostingResponseDto;
import wanted.pre_onboarding.domain.dto.response.GetOnePostingResponseDto;

import java.util.List;

public interface PostingService {

    // 1. 채용공고를 등록합니다.
    void registPosting(RegistPostRequestDto registPostRequestDto);

    // 2. 채용공고를 수정합니다.
    void updatePosting(Long postingId, Long companyId, UpdatePostRequestDto updatePostRequestDto);

    // 3. 채용공고를 삭제합니다.
    void deletePosting(long postingId);

    // 4-1. 채용공고 목록을 가져옵니다.
    List<GetAllPostingResponseDto> getAllPostings();

    // 4-2. 채용공고 검색 기능 구현
    List<GetAllPostingResponseDto> searchPostings(String keyword);

    // 5. 채용 상세 페이지를 가져옵니다.
    GetOnePostingResponseDto getPosting(Long postingId);

    // 6. 사용자는 채용공고에 지원합니다.
}
