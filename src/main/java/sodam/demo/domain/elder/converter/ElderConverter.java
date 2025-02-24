package sodam.demo.domain.elder.converter;

import sodam.demo.domain.elder.dto.ElderRequestDto;
import sodam.demo.domain.elder.dto.ElderResponseDto;
import sodam.demo.domain.elder.entity.Elder;
import sodam.demo.domain.guardian.entity.Guardian;
import sodam.demo.domain.guardian.entity.GuardianElder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElderConverter {

    public static ElderResponseDto.ElderDto toElderDto(Elder elder){
        return ElderResponseDto.ElderDto.builder()
                .elderId(elder.getId())
                .name(elder.getName())
                .build();
    }

    public static ElderResponseDto.ElderListDto toElderListDto(List<GuardianElder> guardianElderList){
        // guardianElderList 비어있으면 빈 리스트를 반환
        // NullPointerException 을 방지 : Guardian 이 담당하는 Elder 가 없을 경우 API 가 안전하게 응답 가능
        if(guardianElderList == null || guardianElderList.isEmpty()){
           return ElderResponseDto.ElderListDto.builder()
                   .elderList(Collections.emptyList())
                   .build();
       }
        // stream API 를 통해 guardianElderList 를 ElderDto 리스트로 반환
        List<ElderResponseDto.ElderDto> elderDtoList = guardianElderList.stream()
                .map(guardianElder -> toElderDto(guardianElder.getElder()))
                .collect(Collectors.toList());
        // elderDtoList 를 ElderListDto 객체로 감싸서 반환
        return ElderResponseDto.ElderListDto.builder()
                .elderList(elderDtoList)
                .build();
    }

    // ElderRegisterDto -> Elder 엔티티 변환
    public static Elder toElder(ElderRequestDto.ElderRegisterDto request) {
        return Elder.builder()
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .build();
    }

    // Elder & Guardian -> GuardianElder 엔티티 변환
    public static GuardianElder toGuardianElder(Guardian guardian, Elder elder){
        return GuardianElder.builder()
                .guardian(guardian)
                .elder(elder)
                .build();
    }

    // Elder & Guardian → ElderRegisterResultDto 변환
    public static ElderResponseDto.ElderRegisterResultDto toElderRegisterResultDto(Elder elder, Guardian guardian){
        return ElderResponseDto.ElderRegisterResultDto.builder()
                .elderId(elder.getId())
                .createdAt(elder.getCreatedAt())
                .build();
    }
}
