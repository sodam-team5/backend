package sodam.demo.domain.elders.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.elders.converter.ElderConverter;
import sodam.demo.domain.elders.domain.Elder;
import sodam.demo.domain.elders.dto.ElderResponseDto;
import sodam.demo.domain.elders.repository.ElderRepository;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.ElderException;

@Service
@RequiredArgsConstructor
public class ElderQueryServiceImpl implements ElderQueryService{
    private final ElderRepository elderRepository;

    public ElderResponseDto.ElderHomeResponseDto getElderHomeInfo(Long elderId){
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ElderException(ErrorStatus.ELDER_NOT_FOUND));

        // 엔티티 -> DTO 변환
        return ElderConverter.toElderHomeResponseDto(elder);
    }
}
