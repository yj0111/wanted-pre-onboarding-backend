package wanted.pre_onboarding.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import wanted.pre_onboarding.domain.dto.request.ApplyRequestDto;
import wanted.pre_onboarding.domain.entity.Apply;
import wanted.pre_onboarding.domain.entity.Member;
import wanted.pre_onboarding.domain.entity.Posting;
import wanted.pre_onboarding.domain.repository.ApplyRepository;
import wanted.pre_onboarding.domain.repository.MemberRepository;
import wanted.pre_onboarding.domain.repository.PostingRepository;
import wanted.pre_onboarding.global.exception.NotExistMemberException;
import wanted.pre_onboarding.global.exception.NotExistPostingException;
import wanted.pre_onboarding.global.exception.alreadyApplyException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;
    private final ApplyRepository applyRepository;

    @Override
    @Transactional
    public void apply(ApplyRequestDto applyRequestDto) {
        Long memberId = applyRequestDto.getMemberId();
        Long postingId = applyRequestDto.getPostingId();

        // 회원 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotExistMemberException::new);

        // 공고 찾기
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(NotExistPostingException::new);

        // 지원 기록 확인
        Apply existingApply = applyRepository.findByMember_MemberIdAndPosting_PostingId(memberId, postingId);

        if (existingApply != null) {
            throw new alreadyApplyException();
        }

        // 지원 기록 저장
        Apply apply = Apply.builder()
                .member(member)
                .posting(posting)
                .isApplied(true)
                .build();

        applyRepository.save(apply);
    }
}
