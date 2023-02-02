package fr.ulco.springshop.model;

public abstract class DTOEntityConverter<Entity, DTO> {
    public abstract Entity convertToEntity(DTO dto);

    public abstract DTO convertToDTO(Entity entity);
}
