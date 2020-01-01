package rs.raf.cloud.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.raf.cloud.domain.user.dto.UserDto;
import rs.raf.cloud.domain.user.entity.User;


@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper( UserMapper.class );

    UserDto userToUserDto(User user);
}
