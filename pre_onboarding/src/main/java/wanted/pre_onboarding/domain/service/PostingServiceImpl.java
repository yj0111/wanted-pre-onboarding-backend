package wanted.pre_onboarding.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.entity.Company;
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

}
