package sodam.demo.domain.stt;

import com.google.cloud.speech.v1.*;

import java.util.List;

public class SpeechToTextExample {
    public static void main(String[] args) throws Exception{
        try(SpeechClient speechClient = SpeechClient.create()){

            // 오디오 파일 경로 설정 (Google Storage)
            String gcsUri = "gs://cloud-samples-data/speech/brooklyn_bridge.raw";

            // 오디오 인식 설정 구성
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)  // 오디오 인코딩 형식
                    .setSampleRateHertz(16000)                              // 샘플 레이트
                    .setLanguageCode("en-US")                               // 한국어 설정
                    .build();

            // Cloud Storage에서 오디오 파일 불러오기
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(gcsUri)
                    .build();

            // Speech-to-Text 요청 수행
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            // 결과 출력
            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("변환된 텍스트: %s%n", alternative.getTranscript());
            }
        }
    }
}
