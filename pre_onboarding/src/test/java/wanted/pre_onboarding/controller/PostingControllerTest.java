package wanted.pre_onboarding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;
import wanted.pre_onboarding.domain.controller.PostingController;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;
import wanted.pre_onboarding.domain.dto.response.GetAllPostingResponseDto;
import wanted.pre_onboarding.domain.dto.response.GetOnePostingResponseDto;
import wanted.pre_onboarding.domain.service.PostingService;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class PostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostingService postingService;

    @Autowired
    private PostingController postingController;

    // 한글이 깨져서 CharacterEncodingFilter 추가
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(postingController)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @DisplayName(value = "공고 등록 테스트")
    @Test
    void registPosting() throws Exception {
        RegistPostRequestDto registPostRequestDto = RegistPostRequestDto.builder()
                .postingId(12L)
                .companyId(1L)
                .postingNation("한국")
                .postingRegion("경기")
                .postingPosition("백엔드")
                .postingBonus("100000원")
                .postingSkills("Java")
                .postingDetail("원티드에서는 백엔드 개발자..")
                .build();

        mockMvc.perform(post("/posting/regist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registPostRequestDto)))
                .andExpect(status().isOk());
    }

    @DisplayName(value = "공고 수정 테스트")
    @Test
    void updatePosting() throws Exception {
        Long postingId = 14L;
        Long companyId = 1L;
        UpdatePostRequestDto updatePostRequestDto = UpdatePostRequestDto.builder()
                .postingNation("미국")
                .postingRegion("캘리포니아")
                .postingPosition("풀스택 개발자")
                .postingBonus("200000원")
                .postingSkills("Java, Python")
                .postingDetail("최신 기술 스택을 활용한...")
                .build();

        mockMvc.perform(patch("/posting/update/{companyId}/{postingId}", companyId, postingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePostRequestDto)))
                .andExpect(status().isOk());
    }

    @DisplayName(value = "공고 삭제 테스트")
    @Test
    void deletePosting() throws Exception {
        Long postingId = 10L;

        mockMvc.perform(delete("/posting/{postingId}", postingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName(value = "공고 상세 조회 테스트")
    @Test
    void getPostingDetail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/posting/{postingId}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        GetOnePostingResponseDto getOnePostingResponseDto = objectMapper.readValue(responseBody, new TypeReference<>() {});

        assertEquals(postingService.getPosting(2L), getOnePostingResponseDto);
    }

    @DisplayName(value = "전체 공고 리스트 테스트")
    @Test
    void getJobPostingList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/posting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        List<GetAllPostingResponseDto> getAllPostingResponseDtoList = objectMapper.readValue(responseBody, new TypeReference<>() {});

        assertEquals(postingService.getAllPostings(), getAllPostingResponseDtoList);
    }

}
