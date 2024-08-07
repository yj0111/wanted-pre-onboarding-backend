package wanted.pre_onboarding.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.domain.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting, Long> {

}
