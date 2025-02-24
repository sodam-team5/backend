package sodam.demo.domain.guardian.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sodam.demo.domain.answer.dto.AnswerResponseDto;
import sodam.demo.domain.answer.service.AnswerQueryService;
import sodam.demo.domain.elder.dto.ElderResponseDto;
import sodam.demo.domain.elder.service.ElderQueryService;
import sodam.demo.domain.guardian.converter.GuardianConverter;
import sodam.demo.domain.guardian.dto.GuardianRequestDto;
import sodam.demo.domain.guardian.dto.GuardianResponseDto;
import sodam.demo.domain.guardian.entity.Guardian;
import sodam.demo.domain.guardian.service.GuardianCommandService;
import sodam.demo.global.apipayload.ApiResponse;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class GuardianController {
    private final ElderQueryService elderQueryService;
    private final AnswerQueryService answerQueryService;
    private final GuardianCommandService guardianCommandService;

    @Operation(summary = "말벗 페이지 - 어르신 목록 조회 API", description = "말벗 페이지에서 말벗이 담당하는 어르신 목록을 조회하는 API입니다.")
    @GetMapping("/{guardianId}/elders")
    public ApiResponse<ElderResponseDto.ElderListDto> getElderList(@PathVariable Long guardianId){
        return ApiResponse.onSuccess(elderQueryService.getElderList(guardianId));
    }

    @Operation(summary = "말벗 페이지 - 어르신 기록일 목록 조회 API", description = "말벗 페이지에서 어르신이 기록을 남긴 날짜 리스트를 반환합니다.")
    @GetMapping("/elders/{elderId}/records")
    public ApiResponse<AnswerResponseDto.AnswerDateListDto> getElderAnswerDateList(@PathVariable Long elderId){
        return ApiResponse.onSuccess(answerQueryService.getElderAnswerDateList(elderId));
    }

    @Operation(summary = "말벗 페이지 - 날짜 별 상세 조회 API", description = "말벗 페이지에서 특정 날짜의 질문과 답변을 조회합니다. 추후에 요약과 대화 추천도 추가하고 답변을 제외할 예정입니다.")
    @GetMapping("/elders/{elderId}/records/{recordDate}")
    public ApiResponse<AnswerResponseDto.AnswerDetailListDto> getElderDetailList(@PathVariable Long elderId,
                                                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate recordDate){
        return ApiResponse.onSuccess(answerQueryService.getElderDetailList(elderId, recordDate));
    }

    @Operation(summary = "말벗 회원가입", description = "말벗이 회원가입을 하는 API입니다.")
    @PostMapping("/signup")
    public ApiResponse<GuardianResponseDto.GuardianSignupResponseDto> signup(@RequestBody GuardianRequestDto.GuardianSignupDto request){
        Guardian guardian = guardianCommandService.signupGuardian(request);
        return ApiResponse.onSuccess(GuardianConverter.toGuardianSignupResultDto(guardian));
    }
}
