package springrest.exam.Utils;

import org.springframework.stereotype.Component;
import springrest.exam.dto.FriendCreateDto;
import springrest.exam.dto.FriendModifyDto;
import springrest.exam.entity.Friend;

public class DtoConvertor {

    public static Friend convert(FriendCreateDto dto){
        return Friend
            .builder()
            .fname(dto.getFname())
            .fage(dto.getFage())
            .build();
    }

    public static Friend convert(FriendModifyDto dto){
        return Friend
            .builder()
            .id(dto.getId())
            .fname(dto.getFname())
            .fage(dto.getFage())
            .build();
    }
}
