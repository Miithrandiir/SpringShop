package fr.ulco.springshop.service;

import fr.ulco.springshop.model.DTOEntityConverter;
import fr.ulco.springshop.model.conveter.UserConverter;
import fr.ulco.springshop.model.dao.UserRepository;
import fr.ulco.springshop.model.dto.UserDTO;
import fr.ulco.springshop.model.entities.UserEntity;
import fr.ulco.springshop.service.core.UserServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public static UserService create(final UserRepository userRepository, final UserConverter userConverter) {
        return new UserService(userRepository, userConverter);
    }


    @Override
    public Optional<UserDTO> findByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.map(userConverter::convertToDTO);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = userRepository.saveAndFlush(userConverter.convertToEntity(userDTO));
        return userConverter.convertToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {

        userDTO.setUpdatedAt(LocalDateTime.now());

        UserEntity user = userRepository.saveAndFlush(userConverter.convertToEntity(userDTO));

        return userConverter.convertToDTO(user);
    }


}
