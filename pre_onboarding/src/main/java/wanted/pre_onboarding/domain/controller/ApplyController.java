package wanted.pre_onboarding.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.pre_onboarding.domain.dto.request.ApplyRequestDto;
import wanted.pre_onboarding.domain.service.ApplyService;
import wanted.pre_onboarding.global.response.ResponseResult;

@Slf4j
@RestController
@RequestMapping(value = "apply")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    // 6. 사용자는 채용공고에 지원합니다.
    @PostMapping(value = "/post")
    public ResponseResult userApply(@RequestBody ApplyRequestDto applyRequestDto) {
        applyService.apply(applyRequestDto);
        return ResponseResult.successResponse;
    }
}