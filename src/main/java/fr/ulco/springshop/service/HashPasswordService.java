package fr.ulco.springshop.service;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import fr.ulco.springshop.service.core.HashPasswordServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HashPasswordService implements HashPasswordServiceInterface {

    public static HashPasswordService create() {
        return new HashPasswordService();
    }

    @Override
    public String hashPassword(String password) {
        // Create instance
        Argon2 argon2 = Argon2Factory.create();
        return argon2.hash(4, 65536, 1 , password.toCharArray());
    }

    @Override
    public boolean comparePassword(String clearPassword, String hashedPassword) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashedPassword, clearPassword.toCharArray());
    }
}
