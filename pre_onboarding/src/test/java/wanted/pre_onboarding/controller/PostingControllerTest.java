package wanted.pre_onboarding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import wanted.pre_onboarding.domain.dto.request.RegistPostRequestDto;
import wanted.pre_onboarding.domain.dto.request.UpdatePostRequestDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
        Long postingId = 12L;
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
        Long postingId = 12L;

        mockMvc.perform(delete("/posting/{postingId}", postingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
