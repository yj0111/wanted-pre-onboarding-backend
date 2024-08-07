package wanted.pre_onboarding.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
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

}
