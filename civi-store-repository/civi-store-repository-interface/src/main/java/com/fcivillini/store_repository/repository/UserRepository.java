package com.fcivillini.store_repository.repository;

import com.fcivillini.store_repository.dao.UserDao;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing users.
 */
public interface UserRepository {

    /**
     * Finds a user by its ID.
     *
     * @param id the ID of the user to find
     * @return an Optional containing the found user, or empty if not found
     */
    Optional<UserDao> findById(Long id);

    /**
     * Saves a new user.
     *
     * @param user the user to save
     * @return the saved user
     */
    UserDao save(UserDao user);

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(Long id);

    /**
     * Finds all users.
     *
     * @return a list of all users
     */
    List<UserDao> findAll();
}