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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .postingId(14L)
                .companyId(1L)
                .postingNation("한국")
                .postingRegion("경기")
                .postingPosition("백엔드")
                .postingBonus("100000원")
                .postingSkills("Java")
                .postingDetail("원티드에서는 백엔드 개발자..")
                .build();

        mockMvc.perform(post("/posting/regist") // Corrected path to start with "/"
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registPostRequestDto)))
                .andExpect(status().isOk());
    }
}
