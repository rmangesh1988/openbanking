package eu.dnb.openbanking.service;

import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.vo.CustomerPatch;
import eu.dnb.openbanking.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by rmang on 15-03-2018.
 */
@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    public Customer findCustomerById(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.isPresent() ? customer.get() : null;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer applyPatch(Customer customer, CustomerPatch customerPatch) {
        BeanUtils.copyProperties(customerPatch, customer);
        customerRepository.save(customer);
        return customer;
    }
}
