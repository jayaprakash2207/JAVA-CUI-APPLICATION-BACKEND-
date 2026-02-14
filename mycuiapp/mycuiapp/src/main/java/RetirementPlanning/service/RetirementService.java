package RetirementPlanning.service;

import RetirementPlanning.exception.RecordNotFoundException;
import RetirementPlanning.exception.RetirementException;
import RetirementPlanning.facade.RetirementFacade;
import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;
import org.apache.log4j.Logger;

import java.util.List;

public class RetirementService {

    private static final Logger logger = Logger.getLogger(RetirementService.class);

    private final RetirementFacade facade = new RetirementFacade();

    public void addCustomer(CustomerVO c) {

        logger.info("Service: addCustomer started");

        try {
            facade.registerCustomer(c);
            System.out.println("Customer is added successfully");

            logger.info("Service: addCustomer completed");

        } catch (RetirementException e) {
            logger.error("Service Error (addCustomer): " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }
    public CustomerVO viewCustomer(int id) {
        logger.info("Service: viewCustomer started, id=" + id);
        try {
            return facade.viewCustomer(id);

        } catch (RecordNotFoundException e) {
            logger.error("Customer not found inn, id=" + id);
            System.out.println("Customer not found");
            return null;

        } catch (RetirementException e) {
            logger.error("Service Error (viewCustomer): " + e.getMessage());
            System.out.println("Error fetching the customer");
            return null;
        }
    }
    public List<CustomerVO> viewAllCustomers() {

        logger.info("Service: viewAllCustomers started");

        try {
            return facade.viewAllCustomers();

        } catch (RetirementException e) {
            logger.error("Service Error (viewAllCustomers): " + e.getMessage());
            System.out.println("Unable to fetch customers");
            return List.of();
        }
    }public void updateCustomer(CustomerVO c) {

        logger.info("Service: updateCustomer started, id=" + c.getCustomerId());

        try {
            facade.updateCustomer(c);
            System.out.println("Customer updaated successfully");

            logger.info("Service: updateCustomer completed");

        } catch (RetirementException e) {
            logger.error("Service Error (updateCustomer): " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }
    public RetirementPlanVO generateRetirementPlan(CustomerVO c) {

        logger.info("Service: generateRetirementPlan started");
        try {
            return facade.generateRetirementPlan(c);

        } catch (RetirementException e) {
            logger.error("Service Error (generateRetirementPlan): " + e.getMessage());
            System.out.println("Failed to generate retirement plan");
            return null;
        }
    }
}
