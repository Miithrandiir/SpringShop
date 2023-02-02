package fr.ulco.springshop.service.core;

import fr.ulco.springshop.model.bo.UserBO;

import java.util.Optional;

public interface UserServiceInterface {

    /**
     * Retrouver un utilisateur par email
     *
     * @param email Email de l'utilisateur recherché
     * @return Utilisateur
     */
    public Optional<UserBO> findByEmail(String email);

    /**
     * Création d'un nouvel utilisateur
     *
     * @param userBO Nouvel utilisateur
     * @return Nouvel Utilisateur
     */
    public UserBO createUser(UserBO userBO);


    /**
     * Mise à jour d'un utilisateur
     *
     * @param userBO Utilisateur mise à jour
     * @return Utilisateur mise à jour
     */
    public UserBO updateUser(UserBO userBO);


}
