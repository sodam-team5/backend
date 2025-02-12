package sodam.demo.domain.elders.converter;

import sodam.demo.domain.elders.domain.Elder;
import sodam.demo.domain.elders.dto.ElderResponseDto;

public class ElderConverter {

    public static ElderResponseDto.ElderHomeResponseDto toElderHomeResponseDto(Elder elder){
        return ElderResponseDto.ElderHomeResponseDto.builder()
                .elderId(elder.getId())
                .name(elder.getName())
                .build();
    }
}
