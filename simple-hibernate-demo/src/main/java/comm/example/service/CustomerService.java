package comm.example.service;

import java.util.List;

import comm.example.entity.Customer;

public interface CustomerService {
	public Customer createCustoemr(Customer customer);
	public List<Customer> getAllCustomers();

}