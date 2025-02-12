package sodam.demo.domain.elder.service;

import sodam.demo.domain.elder.dto.ElderResponseDto;

public interface ElderQueryService {
    ElderResponseDto.ElderDto getElderInfo(Long elderId);
    ElderResponseDto.ElderListDto getElderList(Long guardianId);
}
