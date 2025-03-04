package sodam.demo.domain.member.controller.guardian;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sodam.demo.domain.member.converter.GuardianConverter;
import sodam.demo.domain.member.dto.guardian.GuardianRequestDto;
import sodam.demo.domain.member.dto.guardian.GuardianResponseDto;
import sodam.demo.domain.member.entity.Guardian;
import sodam.demo.domain.member.service.guardian.GuardianCommandService;
import sodam.demo.global.apipayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class GuardianAuthRestController {
    private final GuardianCommandService guardianCommandService;

    @Operation(summary = "말벗 회원가입", description = "말벗이 회원가입을 하는 API입니다.")
    @PostMapping("/signup")
    public ApiResponse<GuardianResponseDto.GuardianSignupResponseDto> signup(@RequestBody GuardianRequestDto.GuardianSignupDto request){
        Guardian guardian = guardianCommandService.signupGuardian(request);
        return ApiResponse.onSuccess(GuardianConverter.toGuardianSignupResultDto(guardian));
    }

    @Operation(summary = "말벗 로그인 API", description = "말벗이 로그인을 하는 API입니다. " +
            "\n"+ "이메일과 비밀번호를 입력해주세요. " +
            "\n"+ "GuardianId와 함께 Access Token과 Refresh Token을 발급합니다.")
    @PostMapping("/login/guardians")
    public ApiResponse<GuardianResponseDto.GuardianLoginResponseDto> Guardianlogin(@RequestBody GuardianRequestDto.GuardianLoginDto request){
        return ApiResponse.onSuccess(guardianCommandService.loginGuardian(request.getEmail(), request.getPassword()));
    }
}
