package com.infnet.DR1_TP2.E04;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void testRegisterCustomer_ValidAge() {
        Customer customer = new Customer(1, "Maria Cichota", "maria.cichota@example.com", 18, true);
        assertTrue(customerService.registerCustomer(customer));

        customer = new Customer(2, "Sandra Silva", "sandra.silva@example.com", 99, true);
        assertTrue(customerService.registerCustomer(customer));
    }

    @Test
    void testRegisterCustomer_InvalidAge() {
        Customer customer = new Customer(3, "Joana", "joana@example.com", 17, true);
        assertFalse(customerService.registerCustomer(customer));

        customer = new Customer(4, "Sofya", "sofya@example.com", 100, true);
        assertFalse(customerService.registerCustomer(customer));
    }

    @Test
    void testUpdateCustomer_ActiveCustomer() {
        Customer customer = new Customer(5, "Diego", "diego@example.com", 25, true);
        assertTrue(customerService.updateCustomer(customer, "Diego Hypolito", "diego.hypolito@example.com", 26));
    }

    @Test
    void testUpdateCustomer_InactiveCustomer() {
        Customer customer = new Customer(6, "Bob", "bob@example.com", 30, false);
        assertFalse(customerService.updateCustomer(customer, "Bob Marley", "bob.marley@example.com", 31));
    }

    @Test
    void testDeleteCustomer_ActiveCustomer() {
        Customer customer = new Customer(7, "Charlie", "charlie@example.com", 40, true);
        assertTrue(customerService.deleteCustomer(customer));
    }

    @Test
    void testDeleteCustomer_InactiveCustomer() {
        Customer customer = new Customer(8, "Dave", "dave@example.com", 45, false);
        assertFalse(customerService.deleteCustomer(customer));
    }

    @Test
    void testRegisterCustomer_ValidEmail() {
        Customer customer = new Customer(9, "Eva", "eva@example.com", 35, true);
        assertTrue(customerService.registerCustomer(customer));
    }

    @Test
    void testRegisterCustomer_InvalidEmail_NoAtSymbol() {
        Customer customer = new Customer(10, "Frank", "frankexample.com", 35, true);
        assertFalse(customerService.registerCustomer(customer));
    }

    @Test
    void testRegisterCustomer_InvalidEmail_NoDomain() {
        Customer customer = new Customer(11, "Grace", "grace@.com", 35, true);
        assertFalse(customerService.registerCustomer(customer));
    }

    @Test
    void testRegisterCustomer_FullValidRegistration() {
        Customer customer = new Customer(12, "William", "william@example.com", 28, true);
        assertTrue(customerService.registerCustomer(customer));
    }
}
