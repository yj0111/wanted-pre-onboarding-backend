package wanted.pre_onboarding.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;
import wanted.pre_onboarding.domain.entity.Company;
import wanted.pre_onboarding.domain.entity.Posting;
import wanted.pre_onboarding.domain.repository.CompanyRepository;
import wanted.pre_onboarding.domain.repository.PostingRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void registPosting(RegistPostRequestDto registPostRequestDto) {
        log.info("PostingServiceImpl_registPosting -> 공고 등록 시도");

        // 회사 ID 조회
        Company company = companyRepository.findById(registPostRequestDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다."));

        postingRepository.save(registPostRequestDto.toPostingEntity(company));
    }

    @Override
    @Transactional
    public void updatePosting(Long postingId, Long companyId, UpdatePostRequestDto updatePostRequestDto) {
        log.info("PostingServiceImpl_updatePosting -> 공고 수정 시도");

        // 회사 ID 조회
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다."));

        // 공고 ID로 공고 조회
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new RuntimeException("공고를 찾을 수 없습니다."));

        log.info("UpdatePostRequestDto: {}", updatePostRequestDto);

        posting.updatePosting(updatePostRequestDto);
        postingRepository.save(posting);
    }

    @Override
    @Transactional
    public void deletePosting(long postingId) {
        log.info("PostingServiceImpl_deletePosting -> 공고 삭제 시도");
        postingRepository.deleteById(postingId);
    }

}
