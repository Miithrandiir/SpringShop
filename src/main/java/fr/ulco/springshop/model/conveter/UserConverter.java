package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.DTOEntityConverter;
import fr.ulco.springshop.model.dto.UserDTO;
import fr.ulco.springshop.model.entities.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter extends DTOEntityConverter<UserEntity, UserDTO> {

    public static UserConverter create() {
        return new UserConverter();
    }

    @Override
    public UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUpdatedAt(userDTO.getUpdatedAt());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setEnabled(userDTO.isEnabled());
        user.setName(userDTO.getName());
        user.setFirstname(userDTO.getFirstname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        return user;
    }


    @Override
    public UserDTO convertToDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getFirstname(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isEnabled()
        );
    }
}
