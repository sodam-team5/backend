package sodam.demo.domain.elder.service;

import sodam.demo.domain.elder.dto.ElderRequestDto;
import sodam.demo.domain.elder.entity.Elder;
import sodam.demo.domain.elder.entity.ElderInterest;

import java.util.List;

public interface ElderCommandService {
    Elder registerElder(Long guardianId, ElderRequestDto.ElderRegisterDto request);
    List<ElderInterest> setElderInterest(Long elderId, List<Long> interestIds);
}
