package sodam.demo.domain.member.controller.elder;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sodam.demo.domain.member.converter.ElderConverter;
import sodam.demo.domain.member.converter.ElderInterestConverter;
import sodam.demo.domain.member.dto.elder.ElderRequestDto;
import sodam.demo.domain.member.dto.elder.ElderResponseDto;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.member.entity.ElderInterest;
import sodam.demo.domain.member.service.elder.ElderCommandService;
import sodam.demo.domain.member.service.elder.ElderQueryService;
import sodam.demo.domain.member.entity.Guardian;
import sodam.demo.domain.member.repository.GuardianRepository;
import sodam.demo.global.apipayload.ApiResponse;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.GuardianException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ElderRestController {
    private final ElderQueryService elderQueryService;
    private final ElderCommandService elderCommandService;
    private final GuardianRepository guardianRepository;

    @Operation(summary = "어르신 첫 화면 API", description = "어르신이 로그인하면 보이는 첫 페이지입니다. 로그인한 사용자 정보를 가져와 이름만 반환합니다." +
            "추후에 elderId를 jwt로 변환할 예정입니다.")
    @GetMapping("/elders/{elderId}/info")
    public ApiResponse<ElderResponseDto.ElderDto> getElderHomeInfo(@PathVariable Long elderId){
            return ApiResponse.onSuccess(elderQueryService.getElderInfo(elderId));
    }

    @Operation(summary = "말벗 - 어르신 계정 등록하기 API", description = "말벗이 어르신 계정을 등록하는 API입니다. 추후에 guardianId를 jwt로 변환할 예정입니다.")
    @PostMapping("/guardians/{guardianId}/register/elders")
    public ApiResponse<ElderResponseDto.ElderRegisterResultDto> registerElder(@PathVariable Long guardianId,
                                                                              @RequestBody ElderRequestDto.ElderRegisterDto request){
        Elder elder = elderCommandService.registerElder(guardianId, request);
        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(()-> new GuardianException(ErrorStatus.GUARDIAN_NOT_FOUND));
        return ApiResponse.onSuccess(ElderConverter.toElderRegisterResultDto(elder, guardian));
    }

    @Operation(summary = "말벗 - 어르신 계정 등록하기 중 관심사 설정하기 API", description = "어르신의 관심사를 등록하는 API입니다. 아예 선택하지 않을 수도 있습니다. 여러 개 선택도 가능합니다.")
    @PostMapping("/elders/{elderId}/interests")
    public ApiResponse<ElderResponseDto.ElderInterestListDto> setElderInterest(@PathVariable Long elderId,
                                                                               @RequestBody ElderRequestDto.ElderInterestDto request){
        List<ElderInterest> elderInterests = elderCommandService.setElderInterest(elderId, request.getInterestIds());
        return ApiResponse.onSuccess(ElderInterestConverter.toElderInterestListDto(elderInterests));
    }
}
