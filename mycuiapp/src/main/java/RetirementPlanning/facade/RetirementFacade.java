package RetirementPlanning.facade;

import RetirementPlanning.dao.CustomerDAO;
import RetirementPlanning.vo.CustomerVO;
import org.apache.log4j.Logger;

import java.util.List;

public class RetirementFacade {

    private static final Logger logger =
            Logger.getLogger(RetirementFacade.class);

    private final CustomerDAO customerDAO = new CustomerDAO();

    // ================= ADD CUSTOMER =================
    public void registerCustomer(CustomerVO c) throws Exception {
        logger.info("Facade: registerCustomer()");
        customerDAO.insertCustomer(c);
    }

    // ================= VIEW BY ID =================
    public CustomerVO viewCustomer(int id) throws Exception {
        logger.info("Facade: viewCustomer(" + id + ")");
        return customerDAO.getCustomerById(id);
    }

    // ================= VIEW ALL =================
    public List<CustomerVO> viewAllCustomers() throws Exception {
        logger.info("Facade: viewAllCustomers()");
        return customerDAO.getAllCustomers();
    }

    // ================= UPDATE =================
    public void updateCustomer(CustomerVO c) throws Exception {
        logger.info("Facade: updateCustomer(ID=" + c.getCustomerId() + ")");
        customerDAO.updateCustomer(c);
    }
}
