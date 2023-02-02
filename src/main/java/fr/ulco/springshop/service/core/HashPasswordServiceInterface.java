package fr.ulco.springshop.service.core;

public interface HashPasswordServiceInterface {
    /**
     * Récupérer le hash du mot de passe
     *
     * @param password Mot de pas en clair
     * @return Hash du mot de passe
     */
    public String hashPassword(String password);

    /**
     * Compare le mot de passe et le hash
     *
     * @param clearPassword  Mot de passe en clair
     * @param hashedPassword Hash du mot de passe de comparaison
     * @return vrai s'ils correspondent
     */
    public boolean comparePassword(String clearPassword, String hashedPassword);
}
