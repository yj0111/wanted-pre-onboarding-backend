package wanted.pre_onboarding.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
