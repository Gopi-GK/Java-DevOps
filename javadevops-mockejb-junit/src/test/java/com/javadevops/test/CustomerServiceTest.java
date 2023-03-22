package com.javadevops.test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import com.javadevops.main.Customer;
import com.javadevops.main.CustomerService;

public class CustomerServiceTest {

	@Mock
	private EntityManager em;

	@InjectMocks
	private CustomerService service = new CustomerService();

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	Customer customer = new Customer();

	@Test
	public void testAddCustomer() {
		customer.setId(5);
		customer.setEmail("seba123@y.com");
		customer.setFirstName("SEBA");
		customer.setLastName("SEBA23");
		assertEquals(customer, service.addCustomer(customer));
		verify(em, times(1)).persist(customer);
	}

	@Test(expected = EJBException.class)
	public void testAddCustomerWithoutName() {
		customer.setEmail("seba123@y.com");
		customer.setFirstName("");
		customer.setLastName("SEBA23");
		service.addCustomer(customer);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testEditNonExistentCustomer() {

		customer.setId(1);
		when(em.merge(customer)).thenThrow(EntityNotFoundException.class);
		service.editCustomer(customer);
	}

	@Test
	public void testDeleteCustomer() {
		
		when(em.find(Customer.class, customer.getId())).thenReturn(customer);
		service.deleteCustomer(customer.getId());
		verify(em, times(1)).remove(customer);
	}

	@Test
	public void testGetCustomer() {
		customer.setId(5);
		
		when(em.find(Customer.class, customer.getId())).thenReturn(customer);
		when(em.find(Customer.class, customer.getFirstName())).thenReturn(customer);
		assertEquals(customer, service.getCustomer(customer.getId()));
		
		verify(em, times(1)).find(Customer.class, customer.getId());
		System.out.println(customer.getId());
	}

}
