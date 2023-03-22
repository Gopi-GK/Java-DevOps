package com.javadevops.main;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerService {
    @PersistenceContext(unitName="my-persistence-unit")
    private EntityManager em;

    public Customer addCustomer(Customer customer) {
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            throw new EJBException("Name cannot be null or empty");
        }
        em.persist(customer);
        return customer;
    }

    public Customer editCustomer(Customer customer) {
        if (em.find(Customer.class, customer.getId()) == null) {
            throw new EntityNotFoundException("Customer with ID " + customer.getId() + " does not exist");
        }
        return em.merge(customer);
    }

    public void deleteCustomer(int id) {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " does not exist");
        }
        em.remove(customer);
    }

    public Customer getCustomer(int id) {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            throw new EntityNotFoundException("Customer with ID " + id + " does not exist");
        }
        return customer;
    }
}


