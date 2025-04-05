package com.fcivillini.store_core.service;

import com.fcivillini.store_core.model.User;
import com.fcivillini.store_interface.exc.StoreException;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Finds a user by its ID.
     *
     * @param id the ID of the user to find
     * @return the found user
     * @throws StoreException if the user cannot be found
     */
    User findById(Long id) throws StoreException;

    /**
     * Finds all users.
     *
     * @return a list of all users
     */
    List<User> findAll();

    /**
     * Saves a new user.
     *
     * @param user the user to save
     * @return the saved user
     */
    User save(User user);

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(Long id);
}