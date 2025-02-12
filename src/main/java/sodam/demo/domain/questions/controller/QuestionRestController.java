package sodam.demo.domain.questions.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sodam.demo.domain.questions.dto.QuestionResponseDto;
import sodam.demo.domain.questions.service.QuestionQueryService;
import sodam.demo.global.apipayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class QuestionRestController {
    private final QuestionQueryService questionQueryService;

    @Operation(summary = "질문 조회 API", description = "사용자 별 맞춤 질문 조회는 추후에 추가할 예정입니다. 일단 Question db에 있는 질문들을 순서대로 조회하는 API입니다.")
    @GetMapping("/questions/{questionId}")
    public ApiResponse<QuestionResponseDto.QuestionDto> getQuestion(@PathVariable Long questionId){
        return ApiResponse.onSuccess(questionQueryService.getQuestion(questionId));
    }

    @Operation(summary = "다음 질문으로 넘어가기 API", description = "다음 질문으로 넘어가는 API입니다. 사용자별 맞춤 질문으로 넘어가도록 구현할 예정입니다. " +
            "지금은 Question db에 있는 질문 table의 다음 질문으로 넘어가도록 구현했습니다.")
    @GetMapping("/questions/next/{questionId}")
    public ApiResponse<QuestionResponseDto.QuestionDto> getNextQuestion(@PathVariable Long questionId){
        return ApiResponse.onSuccess(questionQueryService.getNextQuestion(questionId));
    }
}
