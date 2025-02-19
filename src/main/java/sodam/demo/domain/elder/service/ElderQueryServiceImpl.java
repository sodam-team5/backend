package sodam.demo.domain.elder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.elder.converter.ElderConverter;
import sodam.demo.domain.elder.dto.ElderResponseDto;
import sodam.demo.domain.elder.entity.Elder;
import sodam.demo.domain.elder.repository.ElderRepository;
import sodam.demo.domain.guardian.entity.GuardianElder;
import sodam.demo.domain.guardian.repository.GuardianElderRepository;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.ElderException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElderQueryServiceImpl implements ElderQueryService{
    private final ElderRepository elderRepository;
    private final GuardianElderRepository guardianElderRepository;

    @Override
    public ElderResponseDto.ElderDto getElderInfo(Long elderId){
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(() -> new ElderException(ErrorStatus.ELDER_NOT_FOUND));
        return ElderConverter.toElderDto(elder);
    }

    @Override
    public ElderResponseDto.ElderListDto getElderList(Long guardianId){
        List<GuardianElder> guardianElderList = guardianElderRepository.findByGuardianId(guardianId);
        return ElderConverter.toElderListDto(guardianElderList);
    }
}
