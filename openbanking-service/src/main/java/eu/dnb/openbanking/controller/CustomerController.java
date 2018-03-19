package eu.dnb.openbanking.controller;

import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.vo.CustomerPatch;
import eu.dnb.openbanking.exception.EntityAlreadyExistsException;
import eu.dnb.openbanking.exception.EntityNotFoundException;
import eu.dnb.openbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by rmang on 15-03-2018.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if(customer == null) {
            throw new EntityNotFoundException("Customer with ID: "+customerId+ " not found.");
        }
        return customer;
    }

    @PostMapping("")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
        Customer persistedCustomer = customerService.findCustomerById(customer.getCustomerId());

        if(persistedCustomer != null) {
            throw new EntityAlreadyExistsException("Customer with ID: "+customer.getCustomerId()+" already exists.");
        }

        persistedCustomer = customerService.saveCustomer(customer);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(persistedCustomer.getCustomerId())
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uri);

        return new ResponseEntity<Customer>(persistedCustomer, responseHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("/{customerId}")
    public Customer patchCustomer(@PathVariable String customerId, @RequestBody @Valid CustomerPatch customerPatch) {
        Customer customer = customerService.findCustomerById(customerId);
        if(customer == null) {
            throw new EntityNotFoundException("Customer with ID: "+customerId+ " not found.");
        }

        return customerService.applyPatch(customer, customerPatch);
    }

}
