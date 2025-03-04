package sodam.demo.domain.member.service.elder;

import sodam.demo.domain.member.dto.elder.ElderResponseDto;

public interface ElderQueryService {
    ElderResponseDto.ElderDto getElderInfo(Long elderId);
    ElderResponseDto.ElderListDto getElderList(Long guardianId);
}
