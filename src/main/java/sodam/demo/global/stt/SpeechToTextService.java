package sodam.demo.global.stt;

import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sodam.demo.domain.answer.repository.AnswerRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpeechToTextService {
    /*
        오디오 파일을 Google Speech to Text API를 통해 텍스트로 변환하는 로직이다.
     */
    private final SpeechSettings speechSettings;
    private final AnswerRepository answerRepository;

    public String transcribe(MultipartFile audioFile, int frequency) throws IOException{
        if(audioFile.isEmpty()){
            throw new IOException("오디오 파일이 비었습니다.");
        }

        // 1. 오디오 데이터를 ByteString으로 변환
        byte[] audioBytes = audioFile.getBytes();
        ByteString audioData = ByteString.copyFrom(audioBytes);

        // 2. Recognition 설정 구성
        RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.WEBM_OPUS) // 오디오 형식 지정
                .setSampleRateHertz(frequency) // 주파수 설정
                .setLanguageCode("ko-KR") // 한국어 설정
                .build();

        // 3. Recognition 객체 생성
        RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder()
                .setContent(audioData)
                .build();

        // 4. Google Speech to Text API 호출
        try (SpeechClient speechClient = SpeechClient.create(speechSettings)){
            RecognizeResponse response = speechClient.recognize(recognitionConfig, recognitionAudio);

            // 변환된 텍스트 추출
            StringBuilder transcript = new StringBuilder();
            for(SpeechRecognitionResult result : response.getResultsList()){
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcript.append(alternative.getTranscript()).append(" ");
            }
            return transcript.toString().trim();
        }
    }
}
