package RetirementPlanning.facade;

import RetirementPlanning.bo.RetirementPlanBO;
import RetirementPlanning.exception.RecordNotFoundException;
import RetirementPlanning.exception.RetirementException;
import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;
import org.apache.log4j.Logger;

import java.util.List;

public class RetirementFacade {

    private static final Logger logger = Logger.getLogger(RetirementFacade.class);

    private final RetirementPlanBO planBO =
            new RetirementPlanBO();
    public void registerCustomer(CustomerVO c)
            throws RetirementException {
        logger.info("Facade: registerCustomer invoked");
        planBO.registerCustomer(c);
    }

    public CustomerVO viewCustomer(int id)
            throws RetirementException, RecordNotFoundException {
        logger.info("Facade: viewCustomer invoked, id=" + id);
        return planBO.getCustomerById(id);
    }

    public List<CustomerVO> viewAllCustomers()
            throws RetirementException {
        logger.info("Facade: viewAllCustomers invoked");
        return planBO.getAllCustomers();
    }
    public void updateCustomer(CustomerVO c)
            throws RetirementException {
        logger.info("Facade: updateCustomer invoked, id=" + c.getCustomerId());
        planBO.updateCustomer(c);
    }
    public RetirementPlanVO generateRetirementPlan(CustomerVO c)
            throws RetirementException {
        logger.info("Facade: generateRetirementPlan invoked");
        return planBO.generateAndSavePlan(c);
    }
}
