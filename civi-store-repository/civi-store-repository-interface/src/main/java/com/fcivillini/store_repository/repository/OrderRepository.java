package com.fcivillini.store_repository.repository;

    import com.fcivillini.store_interface.exc.StoreException;
    import com.fcivillini.store_repository.dao.OrderDao;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Optional;

    /**
     * Repository interface for managing orders.
     */
    public interface OrderRepository {

        /**
         * Finds an order by its ID.
         *
         * @param id the ID of the order to find
         * @return an Optional containing the found order, or empty if not found
         */
        Optional<OrderDao> findById(Long id);

        /**
         * Finds orders based on the specified criteria.
         *
         * @param date the date of the orders to find
         * @param userId the ID of the user who placed the orders
         * @param description the description of the orders to find
         * @return a list of orders matching the criteria
         * @throws StoreException if an error occurs while finding the orders
         */
        List<OrderDao> findOrders(LocalDate date, Long userId, String description) throws StoreException;

        /**
         * Saves a new order.
         *
         * @param order the order to save
         * @return the saved order
         */
        OrderDao save(OrderDao order);

        /**
         * Deletes an order by its ID.
         *
         * @param id the ID of the order to delete
         */
        void deleteById(Long id);
    }