package sodam.demo.domain.member.controller.elder;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sodam.demo.domain.member.dto.elder.ElderRequestDto;
import sodam.demo.domain.member.dto.elder.ElderResponseDto;
import sodam.demo.domain.member.service.elder.ElderCommandService;
import sodam.demo.global.apipayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ElderAuthRestController {
    private final ElderCommandService elderCommandService;

//    @Operation(summary = "노인 로그인 API", description = "노인이 입력으로 로그인할 수 있도록 구현한 API입니다. " +
//            "\n"+ "본인의 이름과 보호자의 이름을 입력합니다. " +
//            "\n"+ "ElderId와 함께 Access Token과 Refresh Token을 반환합니다.")
//    @PostMapping("/login/elders")
//    public ApiResponse<ElderResponseDto.ElderLoginResponseDto> Elderlogin(@RequestBody ElderRequestDto.ElderLoginDto request){
//        return ApiResponse.onSuccess(elderCommandService.login(request.getUsername(), request.getGuardianName()));
//    }

//    @Operation(summary = "노인 음성 로그인 API", description = "노인의 음성으로 로그인할 수 있도록 구현한 API입니다. " +
//            "\n" + "본인의 이름과 보호자의 이름을 말합니다. " +
//            "\n" + "ElderId와 함께 Access Token과 Refresh Token을 반환합니다.")
//    @PostMapping("/login/elders/stt")
//    public
}
