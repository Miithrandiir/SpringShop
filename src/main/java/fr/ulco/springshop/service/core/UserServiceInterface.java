package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.dto.UserDTO;

import java.util.Optional;

public interface UserServiceInterface {

    /**
     * Retrouver un utilisateur par email
     *
     * @param email Email de l'utilisateur recherché
     * @return Utilisateur
     */
    public Optional<UserDTO> findByEmail(String email);

    /**
     * Création d'un nouvel utilisateur
     *
     * @param userDTO Nouvel utilisateur
     * @return Nouvel Utilisateur
     */
    public UserDTO createUser(UserDTO userDTO);


    /**
     * Mise à jour d'un utilisateur
     *
     * @param userDTO Utilisateur mise à jour
     * @return Utilisateur mise à jour
     */
    public UserDTO updateUser(UserDTO userDTO);


}
