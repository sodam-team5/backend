package sodam.demo.domain.stt;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sodam.demo.domain.answers.service.AnswerCommandService;
import sodam.demo.global.apipayload.ApiResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stt/answer")
@RequiredArgsConstructor
public class SpeechToTextController {
    /*
        클라이언트로부터 오디오 파일을 받아 변환된 텍스트를 반환
     */
    private final SpeechToTextService speechToTextService;
    private final AnswerCommandService answerCommandService;

    @Operation(summary = "음성 파일을 텍스트로 변환 후 저장하는 API", description = "클라이언트로부터 오디오 파일을 받아 텍스트로 변환하고 Answer db에 저장합니다. " +
            "응답으로는 변환된 텍스트를 보여줍니다. 추후에 elderId를 jwt를 사용하여 토큰으로 정보를 전달할 수 있도록 수정할 예정입니다.")
    @PostMapping(value = "/{questionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Map<String, String>> handleAudioMessage(@RequestParam("audioFile") MultipartFile audioFile,
                                                               @PathVariable("questionId") Long questionId,
                                                               @RequestParam("elderId") Long elderId) {
        try {
            // 1. 오디오 파일을 텍스트로 변환
            String transcript = speechToTextService.transcribe(audioFile, 16000);
            System.out.println("Transcript: " + transcript);

            // 2. 변환된 텍스트를 엔티티로 변환 및 저장
            answerCommandService.saveAnswer(transcript, questionId, elderId);

            // 3. 응답 반환
            Map<String, String> response = new HashMap<>();
            response.put("transcript", transcript);
            return ApiResponse.onSuccess(response);
        } catch (IOException e) {
            return ApiResponse.onFailure("STT_ERROR", "오디오 파일 변환 중 오류가 발생했습니다.", null);
        }
    }
}
