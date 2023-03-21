package com.javadevops.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.javadevops.Customer;


public class CustomerTest {

	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	@Before
	public void setup() {

		entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();

	}

	@After
	public void teardown() {

		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testAddCustomer() {
		// Create a new customer to add
		Customer customer = new Customer();
		// customer.setId(1);
		customer.setEmail("johdoe@123.com");
		customer.setFirstName("John");
		customer.setLastName("Doe");

		// Persist the customer
		entityManager.persist(customer);

		// Check that the customer was added
		assertNotNull(customer.getId());
	}

    @Test
    public void testEditCustomer() {
        // Create a new customer to add
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setLastName("joe");
        customer.setEmail("jane@123.com");
       
        
        // Persist the customer
        entityManager.persist(customer);
        
        // Update the customer's information
        customer.setLastName("johnny");
        
        // Check that the customer was updated
        Customer updatedCustomer = entityManager.find(Customer.class, customer.getId());
        assertNotNull(updatedCustomer);
        assertEquals(customer.getLastName(), updatedCustomer.getLastName());
       
    }
    
    @Test
    public void testDeleteCustomer() {
        // Create a new customer to add
        Customer customer = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Marley");
        customer.setEmail("bob123@yahoo.com");
        
        
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
        customer1.setFirstName("Naveen");
        customer1.setLastName("kumar");
        customer1.setEmail("naveen123@yahoo.com");
        
        Customer customer2 = new Customer();
        customer2.setFirstName("Gopi");
        customer2.setLastName("Krishnan");
        customer2.setEmail("Gopi123@yahoo.com");
        
        // Persist the customers
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        
        // Retrieve all customers
        Query query = entityManager.createQuery("SELECT c FROM Customer c");
        List<Customer> customers = query.getResultList();
        assertNotNull(customers);
        assertEquals(2, customers.size());
        assertEquals(customer1.getFirstName(), customers.get(0).getFirstName());
        assertEquals(customer1.getLastName(), customers.get(0).getLastName());
        assertEquals(customer1.getEmail(), customers.get(0).getEmail());
       
        
        assertEquals(customer2.getFirstName(), customers.get(1).getFirstName());
        assertEquals(customer2.getLastName(), customers.get(1).getLastName());
        assertEquals(customer2.getEmail(), customers.get(1).getEmail());
        
}
}