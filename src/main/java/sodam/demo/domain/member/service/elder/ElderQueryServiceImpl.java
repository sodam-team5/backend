package sodam.demo.domain.member.service.elder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.member.converter.ElderConverter;
import sodam.demo.domain.member.dto.elder.ElderResponseDto;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.member.repository.ElderRepository;
import sodam.demo.domain.member.entity.GuardianElder;
import sodam.demo.domain.member.repository.GuardianElderRepository;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.ElderException;

import java.util.List;

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
