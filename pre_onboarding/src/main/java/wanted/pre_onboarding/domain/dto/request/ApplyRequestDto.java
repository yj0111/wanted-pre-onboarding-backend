package wanted.pre_onboarding.domain.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequestDto {
    private Long memberId;
    private Long postingId;
}
