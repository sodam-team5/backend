package sodam.demo.domain.member.service.elder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sodam.demo.domain.member.converter.ElderConverter;
import sodam.demo.domain.member.converter.ElderInterestConverter;
import sodam.demo.domain.member.dto.elder.ElderRequestDto;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.member.entity.ElderInterest;
import sodam.demo.domain.member.repository.ElderInterestRepository;
import sodam.demo.domain.member.repository.ElderRepository;
import sodam.demo.domain.member.entity.Guardian;
import sodam.demo.domain.member.entity.GuardianElder;
import sodam.demo.domain.member.repository.GuardianElderRepository;
import sodam.demo.domain.member.repository.GuardianRepository;
import sodam.demo.domain.interest.entity.Interest;
import sodam.demo.domain.interest.repository.InterestRepository;
import sodam.demo.global.apipayload.status.ErrorStatus;
import sodam.demo.global.exception.ElderException;
import sodam.demo.global.exception.GuardianException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElderCommandServiceImpl implements ElderCommandService{
    private final GuardianRepository guardianRepository;
    private final ElderRepository elderRepository;
    private final GuardianElderRepository guardianElderRepository;
    private final InterestRepository interestRepository;
    private final ElderInterestRepository elderInterestRepository;

    @Override
    public Elder registerElder(Long guardianId, ElderRequestDto.ElderRegisterDto request){
        Guardian guardian = guardianRepository.findById(guardianId)
                .orElseThrow(()-> new GuardianException(ErrorStatus.GUARDIAN_NOT_FOUND));
        Elder newElder = ElderConverter.toElder(request);
        elderRepository.save(newElder);
        GuardianElder guardianElder = ElderConverter.toGuardianElder(guardian, newElder);
        guardianElderRepository.save(guardianElder);
        return newElder;
    }

    @Override
    @Transactional
    public List<ElderInterest> setElderInterest(Long elderId, List<Long> interestIds){
        Elder elder = elderRepository.findById(elderId)
                .orElseThrow(()-> new ElderException(ErrorStatus.ELDER_NOT_FOUND));
        List<Interest> interests = interestRepository.findAllById(interestIds);
        List<ElderInterest> elderInterests = interests.stream()
                .map(interest -> ElderInterestConverter.toElderInterest(elder, interest))
                .collect(Collectors.toList());
        return elderInterestRepository.saveAll(elderInterests);
    }


}
