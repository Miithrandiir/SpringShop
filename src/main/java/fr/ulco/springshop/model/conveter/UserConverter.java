package fr.ulco.springshop.model.conveter;

import fr.ulco.springshop.model.AbstractBOEntityConverter;
import fr.ulco.springshop.model.bo.UserBO;
import fr.ulco.springshop.model.entities.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter extends AbstractBOEntityConverter<UserEntity, UserBO> {

    public static UserConverter create() {
        return new UserConverter();
    }

    @Override
    public UserEntity convertToEntity(UserBO userBO) {
        UserEntity user = new UserEntity();
        user.setId(userBO.getId());
        user.setUpdatedAt(userBO.getUpdatedAt());
        user.setCreatedAt(userBO.getCreatedAt());
        user.setEnabled(userBO.isEnabled());
        user.setName(userBO.getName());
        user.setFirstname(userBO.getFirstname());
        user.setPassword(userBO.getPassword());
        user.setEmail(userBO.getEmail());
        user.setRole(userBO.getRole());

        return user;
    }


    @Override
    public UserBO convertToBO(UserEntity entity) {
        return new UserBO(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getFirstname(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.isEnabled(),
                entity.getRole()
        );
    }
}
