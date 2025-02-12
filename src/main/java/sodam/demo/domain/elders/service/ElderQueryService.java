package sodam.demo.domain.elders.service;

import sodam.demo.domain.elders.dto.ElderResponseDto;

public interface ElderQueryService {
    ElderResponseDto.ElderHomeResponseDto getElderHomeInfo(Long elderId);
}
