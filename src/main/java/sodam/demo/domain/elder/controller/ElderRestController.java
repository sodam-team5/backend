package sodam.demo.domain.elder.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sodam.demo.domain.elder.dto.ElderResponseDto;
import sodam.demo.domain.elder.service.ElderQueryService;
import sodam.demo.global.apipayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class ElderRestController {
    private final ElderQueryService elderQueryService;

    @Operation(summary = "어르신 첫 화면 API", description = "어르신이 로그인하면 보이는 첫 페이지입니다. 로그인한 사용자 정보를 가져와 이름만 반환합니다." +
            "추후에 memberId를 jwt로 변환할 예정입니다.")
    @GetMapping("/home/{elderId}")
    public ApiResponse<ElderResponseDto.ElderDto> getElderHomeInfo(@PathVariable Long elderId){
            return ApiResponse.onSuccess(elderQueryService.getElderInfo(elderId));
    }
}
