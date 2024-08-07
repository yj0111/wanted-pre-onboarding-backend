package wanted.pre_onboarding.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;
import wanted.pre_onboarding.domain.dto.response.GetAllPostingResponseDto;
import wanted.pre_onboarding.domain.dto.response.GetOnePostingResponseDto;
import wanted.pre_onboarding.domain.entity.Company;
import wanted.pre_onboarding.domain.entity.Posting;
import wanted.pre_onboarding.domain.repository.CompanyRepository;
import wanted.pre_onboarding.domain.repository.PostingRepository;
import wanted.pre_onboarding.global.response.SingleResponseResult;

import java.rmi.ServerException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;
    private final CompanyRepository companyRepository;

    // 1. 채용공고를 등록합니다.
    @Override
    @Transactional
    public void registPosting(RegistPostRequestDto registPostRequestDto) {
        log.info("PostingServiceImpl_registPosting -> 공고 등록 시도");

        // 회사 ID 조회
        Company company = companyRepository.findById(registPostRequestDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다."));

        postingRepository.save(registPostRequestDto.toPostingEntity(company));
    }

    // 2. 채용공고를 수정합니다.
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

    // 3. 채용공고를 삭제합니다.
    @Override
    @Transactional
    public void deletePosting(long postingId) {
        log.info("PostingServiceImpl_deletePosting -> 공고 삭제 시도");
        postingRepository.deleteById(postingId);
    }

    // 4-1. 채용공고 목록을 가져옵니다.
    @Override
    @Transactional
    public List<GetAllPostingResponseDto> getAllPostings() {
        log.info("PostingServiceImpl_getAllPostings -> 모든 공고 조회 시도");

        List<Posting> postings = postingRepository.findAll();
        return postings.stream()
                .map(posting -> new GetAllPostingResponseDto(
                        posting.getPostingId(),
                        posting.getCompany().getCompanyName(),
                        posting.getPostingNation(),
                        posting.getPostingRegion(),
                        posting.getPostingPosition(),
                        posting.getPostingBonus(),
                        posting.getPostingSkills()
                ))
                .collect(Collectors.toList());
    }

    // 4-2. 채용공고 검색 기능 구현
    @Override
    public List<GetAllPostingResponseDto> searchPostings(String keyword) {
        log.info("PostingServiceImpl_searchPostings -> 채용 공고 검색 기능 시도");

        List<Posting> postings = postingRepository.searchPostings(keyword);

        return postings.stream()
                .map(posting -> new GetAllPostingResponseDto(
                        posting.getPostingId(),
                        posting.getCompany().getCompanyName(),
                        posting.getPostingNation(),
                        posting.getPostingRegion(),
                        posting.getPostingPosition(),
                        posting.getPostingBonus(),
                        posting.getPostingSkills()
                ))
                .collect(Collectors.toList());
    }

    // 5. 채용 상세 페이지를 가져옵니다.
    @Override
    public GetOnePostingResponseDto getPosting(Long postingId) {
        log.info("PostingServiceImpl_getOnePosting -> 공고 상세 조회 시도");

        // 공고 ID로 공고 조회
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new RuntimeException("공고를 찾을 수 없습니다."));

        // 회사가 올린 다른 채용 공고 ID 리스트 찾기
        Long companyId = posting.getCompany().getCompanyId();

        List<Long> otherPostingList = postingRepository.findByCompany_CompanyId(companyId).stream()
                .filter(p -> !p.getPostingId().equals(postingId)) //현재 보고 있는 공고는 제외하기
                .map(Posting::getPostingId)
                .collect(Collectors.toList());

        log.info("회사가 올린 다른 채용 공고" + otherPostingList);

        return new GetOnePostingResponseDto(
                posting.getPostingId(),
                posting.getCompany().getCompanyName(),
                posting.getPostingNation(),
                posting.getPostingRegion(),
                posting.getPostingPosition(),
                posting.getPostingBonus(),
                posting.getPostingSkills(),
                posting.getPostingDetail(),
                otherPostingList
        );
    }
}
