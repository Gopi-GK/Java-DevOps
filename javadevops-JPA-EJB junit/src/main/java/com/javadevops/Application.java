package com.javadevops;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        // Initialize the EntityManagerFactory
        EntityManagerUtil.initialize();

        // Create a new customer
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");

        // Persist the customer to the database
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();

//        // Find the customer by ID
//        Customer retrievedCustomer = entityManager.find(Customer.class, customer.getId());
//        System.out.println("Retrieved customer: " + retrievedCustomer.getFirstName());
//
//        // Delete the customer from the database
//        entityManager.getTransaction().begin();
//        entityManager.remove(retrievedCustomer);
//        entityManager.getTransaction().commit();

        // Close the EntityManagerFactory
        EntityManagerUtil.close();
    }
}
