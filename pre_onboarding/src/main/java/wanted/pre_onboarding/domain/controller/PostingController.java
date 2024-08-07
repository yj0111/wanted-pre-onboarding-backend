package wanted.pre_onboarding.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;
import wanted.pre_onboarding.domain.dto.response.GetAllPostingResponseDto;
import wanted.pre_onboarding.domain.service.PostingService;
import wanted.pre_onboarding.global.response.ListResponseResult;
import wanted.pre_onboarding.global.response.ResponseResult;
import wanted.pre_onboarding.global.response.SingleResponseResult;

import java.util.List;

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

    // 3. 채용공고 삭제
    @DeleteMapping("/{postingId}")
    public ResponseResult deletePosting(@PathVariable Long postingId) {
        log.info("PostingController_deletePosting -> 공고 삭제 시작");
        postingService.deletePosting(postingId);
        return ResponseResult.successResponse;
    }

    // 4-1. 채용공고 목록을 가져옵니다.
    @GetMapping("")
    public ListResponseResult<GetAllPostingResponseDto> getAllPostings() {
        log.info("PostingController_getAllPostings -> 모든 공고 조회 시작");
        List<GetAllPostingResponseDto> postings = postingService.getAllPostings();
        return new ListResponseResult<>(postings);
    }

    // 4-2. 채용공고 검색 기능 구현
    @GetMapping("/search") //
    public ResponseResult searchPostings(@RequestParam("keyword") String keyword) {
        log.info("PostingController_searchPostings -> 검색어로 조회 시작 / 검색어 : {}", keyword);
        List<GetAllPostingResponseDto> result = postingService.searchPostings(keyword);
        return new SingleResponseResult<>(result);
    }

    // 5. 채용 상세 페이지를 가져옵니다.
    @GetMapping("{postingId}")
    public ResponseResult getPosting(@PathVariable Long postingId){
        log.info("PostingController_getPostings -> 공고 상세 조회 시작");
        return new SingleResponseResult<>(postingService.getPosting(postingId));
    }
}
