package eu.dnb.openbanking.client;

import eu.dnb.openbanking.domain.Customer;
import eu.dnb.openbanking.domain.vo.CustomerPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rmang on 16-03-2018.
 */
public class OpenBankingService {

    public static final Logger logger = LoggerFactory.getLogger(OpenBankingService.class);

    public String baseUrl;

    public RestTemplate restTemplate;

    public OpenBankingService(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * Method to get a customer having a particular customer ID
     *
     * @param customerId Identifier of the customer
     * @return Returns the fetched ResponseEntity<Customer>
     */
    public ResponseEntity<Customer> getCustomer(String customerId) {
        logger.info("Customer id : "+customerId);
        return restTemplate.getForEntity(baseUrl + "/customers/{customerId}", Customer.class, customerId);
    }

    /**
     * Method to patch the sent in details on the customer identified by the customer ID
     *
     * @param customerId Identifier of the customer
     * @param customerPatch Patch of data to be updated on the customer
     * @return Returns the ResponseEntity<Customer> as it should look after patching new data
     */
    public ResponseEntity<Customer> patchCustomer(String customerId, CustomerPatch customerPatch) {
        logger.info("Customer id : "+customerId + ", Customer patch : "+customerPatch);
        HttpEntity<CustomerPatch> customerPatchHttpEntity = new HttpEntity<CustomerPatch>(customerPatch);
        return restTemplate.exchange(baseUrl + "/customers/{customerId}", HttpMethod.PATCH, customerPatchHttpEntity,  Customer.class, customerId);
    }

}
