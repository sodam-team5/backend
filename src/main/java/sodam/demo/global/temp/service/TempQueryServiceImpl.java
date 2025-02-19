package sodam.demo.global.temp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.TempException;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService{

    @Override
    public void checkFlag(Integer flag){
        if(flag == 1){
            throw new TempException(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
