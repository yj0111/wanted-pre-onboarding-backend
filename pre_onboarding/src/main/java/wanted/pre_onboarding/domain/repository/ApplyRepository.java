package wanted.pre_onboarding.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.domain.entity.Apply;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Apply findByMember_MemberIdAndPosting_PostingId(Long memberId, Long postingId);
}
