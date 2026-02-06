package RetirementPlanning.facade;

import RetirementPlanning.dao.CustomerDAO;
import RetirementPlanning.vo.CustomerVO;
import org.apache.log4j.Logger;

import java.util.List;

public class RetirementFacade {

    private static final Logger logger =
            Logger.getLogger(RetirementFacade.class);

    private final CustomerDAO dao = new CustomerDAO();

    public void registerCustomer(CustomerVO c) throws Exception {
        logger.info("Facade: registerCustomer()");
        dao.insertCustomer(c);
    }

    public CustomerVO viewCustomer(int id) throws Exception {
        logger.info("Facade: viewCustomer(" + id + ")");
        return dao.getCustomerById(id);
    }

    public List<CustomerVO> viewAllCustomers() throws Exception {
        logger.info("Facade: viewAllCustomers()");
        return dao.getAllCustomers();
    }

    public void updateCustomer(CustomerVO c) throws Exception {
        logger.info("Facade: updateCustomer(" + c.getCustomerId() + ")");
        dao.updateCustomer(c);
    }

    public void deleteCustomer(int id) throws Exception {
        logger.info("Facade: deleteCustomer(" + id + ")");
        dao.deleteCustomer(id);
    }

    public void deleteAllCustomers() throws Exception {
        logger.warn("Facade: deleteAllCustomers()");
        dao.deleteAllCustomers();
    }
}
