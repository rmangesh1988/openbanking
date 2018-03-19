package eu.dnb.openbanking.repository;

import eu.dnb.openbanking.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rmang on 15-03-2018.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{
}
