package spock.tests;

import entities.Customer;
import entities.CustomerReader;
import javax.persistence.EntityManager
import spock.lang.Specification;

public class CustomerReaderSpec extends Specification{

  public void "customer full name is formed from first name and last name"() {
    given: "a customer with example name values"
    Customer sampleCustomer = new Customer();
    sampleCustomer.setFirstName("Susan");
    sampleCustomer.setLastName("Ivanova");

    and: "an entity manager that always returns this customer"
    EntityManager entityManager = Stub(EntityManager.class)
    entityManager.find(Customer.class,1L) >> sampleCustomer

    and: "a customer reader which is the class under test"
    CustomerReader customerReader = new CustomerReader()
    customerReader.setEntityManager(entityManager)

    when: "we ask for the full name of the customer"
    String fullName = customerReader.findFullName(1L)

    then: "we get both the first and the last name"
    fullName == "Susan Ivanova"
  }

}