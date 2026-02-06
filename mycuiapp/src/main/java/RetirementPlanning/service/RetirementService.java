package RetirementPlanning.service;

import RetirementPlanning.facade.RetirementFacade;
import RetirementPlanning.vo.CustomerVO;
import org.apache.log4j.Logger;

import java.util.List;

public class RetirementService {

    private static final Logger logger =
            Logger.getLogger(RetirementService.class);

    private final RetirementFacade facade = new RetirementFacade();

    public void addCustomer(CustomerVO c) throws Exception {
        logger.info("Service: addCustomer()");
        facade.registerCustomer(c);
        logger.info("Service: addCustomer completed");
    }

    public CustomerVO viewCustomer(int id) throws Exception {
        logger.info("Service: viewCustomer(" + id + ")");
        return facade.viewCustomer(id);
    }

    public List<CustomerVO> viewAllCustomers() throws Exception {
        logger.info("Service: viewAllCustomers()");
        return facade.viewAllCustomers();
    }

    public void updateCustomer(CustomerVO c) throws Exception {
        logger.info("Service: updateCustomer(" + c.getCustomerId() + ")");
        facade.updateCustomer(c);
    }

    public void deleteCustomer(int id) throws Exception {
        logger.info("Service: deleteCustomer(" + id + ")");
        facade.deleteCustomer(id);
    }

    public void deleteAllCustomers() throws Exception {
        logger.warn("Service: deleteAllCustomers()");
        facade.deleteAllCustomers();
    }
}
