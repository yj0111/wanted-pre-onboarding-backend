package wanted.pre_onboarding.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wanted.pre_onboarding.domain.entity.Posting;

import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    List<Posting> findByCompany_CompanyId(Long companyId);

    @Query(value = "SELECT p.* FROM posting p " +
            "JOIN company c ON p.company_id = c.company_id " +
            "WHERE c.company_name LIKE %:keyword% OR " +
            "p.posting_position LIKE %:keyword% OR " +
            "p.posting_skills LIKE %:keyword%", nativeQuery = true)
    List<Posting> searchPostings(@Param("keyword") String keyword);

}
