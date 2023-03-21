package com.javadevops.jpasample;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class customerTest {
    
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    
    @Before
    public void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }
    
    @After
    public void teardown() {
        transaction.rollback();
        entityManager.close();
        entityManagerFactory.close();
    }
    
    @Test
    public void testAddCustomer() {
        // Create a new customer to add
        Customer customer = new Customer();
        customer.setName("John");
        customer.setAge(30);
        customer.setAddress("123 Main St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setZip("12345");
        
        // Persist the customer
        entityManager.persist(customer);
        
        // Check that the customer was added
        assertNotNull(customer.getId());
    }
    
    @Test
    public void testEditCustomer() {
        // Create a new customer to add
        Customer customer = new Customer();
        customer.setName("Jane");
        customer.setAge(25);
        customer.setAddress("456 Elm St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setZip("12345");
        
        // Persist the customer
        entityManager.persist(customer);
        
        // Update the customer's information
        customer.setAge(30);
        customer.setZip("54321");
        
        // Check that the customer was updated
        Customer updatedCustomer = entityManager.find(Customer.class, customer.getId());
        assertNotNull(updatedCustomer);
        assertEquals(customer.getAge(), updatedCustomer.getAge());
        assertEquals(customer.getZip(), updatedCustomer.getZip());
    }
    
    @Test
    public void testDeleteCustomer() {
        // Create a new customer to add
        Customer customer = new Customer();
        customer.setName("Bob");
        customer.setAge(40);
        customer.setAddress("789 Oak St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setZip("12345");
        
        // Persist the customer
        entityManager.persist(customer);
        
        // Delete the customer
        entityManager.remove(customer);
        
        // Check that the customer was deleted
        Customer deletedCustomer = entityManager.find(Customer.class, customer.getId());
        assertNull(deletedCustomer);
    }
    
    @Test
    public void testGetCustomers() {
        // Create some customers to add
        Customer customer1 = new Customer();
        customer1.setName("John");
        customer1.setAge(30);
        customer1.setAddress("123 Main St");
        customer1.setCity("Anytown");
        customer1.setState("CA");
        customer1.setZip("12345");
        
        Customer customer2 = new Customer();
        customer2.setName("Jane");
        customer2.setAge(25);
        customer2.setAddress("456 Elm St");
        customer2.setCity("Anytown");
        customer2.setState("CA");
        customer2.setZip("12345");
        
        // Persist the customers
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        
        // Retrieve all customers
        Query query = entityManager.createQuery("SELECT c FROM Customer c");
        List<Customer> customers = query.getResultList();
        assertNotNull(customers);
        assertEquals(2, customers.size());
        assertEquals(customer1.getName(), customers.get(0).getName());
        assertEquals(customer1.getAge(), customers.get(0).getAge());
        assertEquals(customer1.getAddress(), customers.get(0).getAddress());
        assertEquals(customer1.getCity(), customers.get(0).getCity());
        assertEquals(customer1.getState(), customers.get(0).getState());
        assertEquals(customer1.getZip(), customers.get(0).getZip());
        
        assertEquals(customer2.getName(), customers.get(1).getName());
        assertEquals(customer2.getAge(), customers.get(1).getAge());
        assertEquals(customer2.getAddress(), customers.get(1).getAddress());
        assertEquals(customer2.getCity(), customers.get(1).getCity());
        assertEquals(customer2.getState(), customers.get(1).getState());
        assertEquals(customer2.getZip(), customers.get(1).getZip());
    }
