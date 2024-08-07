package wanted.pre_onboarding.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;
import wanted.pre_onboarding.domain.service.PostingService;
import wanted.pre_onboarding.global.response.ResponseResult;

@Slf4j
@RestController
@RequestMapping(value = "posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    // 1. 채용공고를 등록합니다.
    @PostMapping("/regist")
    public ResponseResult registPosting(@RequestBody RegistPostRequestDto registPostRequestDto) {
        log.info("PostingController_registPosting -> 공고 등록 시작");
        postingService.registPosting(registPostRequestDto);
        return ResponseResult.successResponse;
    }

    // 2. 채용공고를 수정합니다.
    @PatchMapping("/update/{companyId}/{postingId}")
    public ResponseResult updatePosting(@PathVariable Long postingId, @PathVariable Long companyId ,@RequestBody UpdatePostRequestDto updatePostRequestDto) {
        log.info("PostingController_updatePosting -> 공고 수정 시작");
        postingService.updatePosting(postingId, companyId, updatePostRequestDto);
        return ResponseResult.successResponse;
    }

}
