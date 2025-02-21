package sodam.demo.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.SpeechSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GoogleCloudConfig {
    /*
        GCP 인증 및 Speech-to-Text 서비스의 SpeechSettings 를 구성하는 코드
        GCP JSON 키 파일을 읽어 인증 정보를 제공하여 API 호출 시 사용할 수 있도록 설정함
     */
    @Value("classpath:stt.json")
    private Resource gcsCredentials;

    @Bean
    public SpeechSettings speechSettings(){
        try{
            GoogleCredentials credentials;
            if(System.getenv("GOOGLE_APPLICATION_CREDENTIALS") != null){
                credentials = GoogleCredentials.getApplicationDefault();
                System.out.println("GOOGLE_APPLICATION_CREDENTIALS 환경 변수 사용");
            }else{
                // 환경 변수가 없으면 JSON 파일 직접 로드 (로컬 용)
                credentials = GoogleCredentials.fromStream(gcsCredentials.getInputStream());
                System.out.println("환경 변수 없어 stt.json 파일 사용");
            }
            return SpeechSettings.newBuilder().setCredentialsProvider(()-> credentials).build();
        } catch(Exception e){
            throw new RuntimeException("GCP Speech-to-Text 설정 중 오류가 발생했습니다.", e);
        }
    }
}
