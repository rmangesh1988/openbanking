package eu.dnb.openbanking;

import eu.dnb.openbanking.domain.Address;
import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.CustomerEngagement;
import eu.dnb.openbanking.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

/**
 * Created by rmang on 15-03-2018.
 */
@SpringBootApplication
@EnableWebMvc
public class OpenBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenBankingApplication.class, args);
    }

    //To create dummy customer
    @Bean
    CommandLineRunner init(CustomerService customerService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Customer customer = createDummyCustomer();
                customerService.saveCustomer(customer);
            }

            private Customer createDummyCustomer() {
                Customer customer = new Customer();
                customer.setCustomerId("12345678902");
                customer.setCustomerType(Customer.CustomerType.PERSON);
                customer.setFirstName("Rune");
                customer.setLastName("Bjerke");
                customer.setAddress(createAddress());
                customer.setEmail("rune@dnb.com");
                customer.setPhone("+474445555");
                customer.setCountryCitizenship("NO");
                customer.setCountryOfBirth("NO");
                customer.setCountryTax("NO");
                customer.setCustomerEngagements(Arrays.asList(createCustomerEngagement()));
                return customer;
            }

            private CustomerEngagement createCustomerEngagement() {
                CustomerEngagement customerEngagement = new CustomerEngagement();
                customerEngagement.setAccountNumber("12345678901");
                customerEngagement.setEngagementType(CustomerEngagement.EngagementType.DEPOSIT);
                customerEngagement.setCorporate(true);
                customerEngagement.setEngagementId("2222");
                customerEngagement.setFriendlyName("SAGA Mastercard");
                return customerEngagement;
            }

            private Address createAddress() {
                Address address = new Address();
                address.setPostalAddressLine1("Dronning Eufemias gate 30");
                address.setPostalAddressLine2("c/o CEO office");
                address.setPostalAddressLine3("postal address 3");
                address.setPostCode("0357");
                address.setPostCity("Oslo");
                address.setPostCountry("NO");
                return address;
            }
        };
    }
}
