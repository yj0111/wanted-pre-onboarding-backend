package wanted.pre_onboarding.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.pre_onboarding.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
