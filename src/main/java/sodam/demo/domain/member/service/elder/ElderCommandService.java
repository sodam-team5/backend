package sodam.demo.domain.member.service.elder;

import sodam.demo.domain.member.dto.elder.ElderRequestDto;
import sodam.demo.domain.member.entity.Elder;
import sodam.demo.domain.member.entity.ElderInterest;

import java.util.List;

public interface ElderCommandService {
    Elder registerElder(Long guardianId, ElderRequestDto.ElderRegisterDto request);
    List<ElderInterest> setElderInterest(Long elderId, List<Long> interestIds);
}
