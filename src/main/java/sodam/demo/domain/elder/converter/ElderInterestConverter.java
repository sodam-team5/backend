package sodam.demo.domain.elder.converter;

import org.springframework.stereotype.Component;
import sodam.demo.domain.elder.dto.ElderResponseDto;
import sodam.demo.domain.elder.entity.Elder;
import sodam.demo.domain.elder.entity.ElderInterest;
import sodam.demo.domain.interest.entity.Interest;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElderInterestConverter {

    public static ElderInterest toElderInterest(Elder elder, Interest interest){
        return ElderInterest.builder()
                .elder(elder)
                .interest(interest)
                .build();
    }

    public static ElderResponseDto.ElderInterestDto toElderInterestDto(ElderInterest elderInterest){
        return ElderResponseDto.ElderInterestDto.builder()
                .interestId(elderInterest.getInterest().getId())
                .name(elderInterest.getInterest().getName())
                .build();
    }

    public static ElderResponseDto.ElderInterestListDto toElderInterestListDto(List<ElderInterest> elderInterests){
        return ElderResponseDto.ElderInterestListDto.builder()
                .interests(elderInterests.stream()
                        .map(ElderInterestConverter::toElderInterestDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
