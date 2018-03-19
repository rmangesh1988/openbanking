package eu.dnb.openbanking.controller;

import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.vo.CustomerPatch;
import eu.dnb.openbanking.service.CustomerService;
import eu.dnb.openbanking.util.TestDataHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static eu.dnb.openbanking.util.TestUtil.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by rmang on 16-03-2018.
 */
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetCustomerSuccess() throws Exception {
        String customerId = "12345678902";
        Customer customer = TestDataHelper.createCustomer(customerId);
        when(customerService.findCustomerById(customerId)).thenReturn(customer);
        mockMvc.perform(get("/customers/{customerId}", customerId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON_UTF8)))
                .andExpect(jsonPath("$.customerId", is(customerId)));
        verify(customerService, times(1)).findCustomerById(customerId);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testGetCustomerFailure() throws Exception {
        String customerId = "12345678999";
        when(customerService.findCustomerById(customerId)).thenReturn(null);
        mockMvc.perform(get("/customers/{customerId}", customerId))
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).findCustomerById(customerId);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testSaveCustomer() throws Exception {
        String customerId = "12345678902";
        Customer customer = TestDataHelper.createCustomer(customerId);
        when(customerService.findCustomerById(customerId)).thenReturn(null);
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);
        mockMvc.perform(post("/customers")
                .contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON)).content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId", is(customerId)))
                .andExpect(header().string("location", containsString("http://localhost/customers/"+customerId)));
        verify(customerService, times(1)).findCustomerById(customerId);
        verify(customerService, times(1)).saveCustomer(any(Customer.class));
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testSaveCustomerFailure() throws Exception {
        String customerId = "12345678902";
        Customer customer = TestDataHelper.createCustomer(customerId);
        when(customerService.findCustomerById(customerId)).thenReturn(customer);
        when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);
        mockMvc.perform(post("/customers")
                .contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON)).content(asJsonString(customer)))
                .andExpect(status().isBadRequest());
        verify(customerService, times(1)).findCustomerById(customerId);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void testPatchCustomerSuccess() throws Exception {
        String customerId = "12345678901";
        String newEmail = "b@b.com";
        String newPhone = "+47888888";
        Customer customer = TestDataHelper.createCustomer(customerId);
        Customer updatedCustomer = TestDataHelper.createCustomer(customerId);
        updatedCustomer.setEmail(newEmail);
        updatedCustomer.setPhone(newPhone);

        CustomerPatch customerPatch = new CustomerPatch();
        customerPatch.setEmail(newEmail);
        customerPatch.setPhone(newPhone);

        when(customerService.findCustomerById(customerId)).thenReturn(customer);
        when(customerService.applyPatch(any(Customer.class), any(CustomerPatch.class))).thenReturn(updatedCustomer);
        mockMvc.perform(patch("/customers/{customerId}", customerId)
        .contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON))
        .content(asJsonString(customerPatch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(newEmail)))
                .andExpect(jsonPath("$.phone", is(newPhone)));
        verify(customerService, times(1)).findCustomerById(customerId);
        verify(customerService, times(1)).applyPatch(any(Customer.class), any(CustomerPatch.class));
        verifyNoMoreInteractions(customerService);

    }

    @Test
    public void testPatchCustomerFailure() throws Exception {
        String customerId = "12345678901";

        CustomerPatch customerPatch = new CustomerPatch();
        customerPatch.setEmail("a@a.com");
        customerPatch.setPhone("234234324");

        when(customerService.findCustomerById(customerId)).thenReturn(null);
        mockMvc.perform(patch("/customers/{customerId}", customerId)
        .contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON))
        .content(asJsonString(customerPatch)))
                .andExpect(status().isNotFound());
        verify(customerService, times(1)).findCustomerById(customerId);
        verifyNoMoreInteractions(customerService);
    }
}
