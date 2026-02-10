package RetirementPlanning.service;

import RetirementPlanning.bo.RetirementPlanBO;
import RetirementPlanning.facade.RetirementFacade;
import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class RetirementService {

    private static final Logger logger =
            Logger.getLogger(RetirementService.class);

    private final RetirementFacade facade = new RetirementFacade();

    // ================= VALIDATIONS =================
    public boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z ]{3,}");
    }

    public boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    public boolean isValidDOB(Date dob) {
        Date today = new Date(System.currentTimeMillis());
        return dob.before(today);
    }

    // ================= CRUD =================
    public void addCustomer(CustomerVO c) throws Exception {
        logger.info("Service: addCustomer()");
        facade.registerCustomer(c);
    }

    public CustomerVO viewCustomer(int id) throws Exception {
        return facade.viewCustomer(id);
    }

    public List<CustomerVO> viewAllCustomers() throws Exception {
        return facade.viewAllCustomers();
    }

    public void updateCustomer(CustomerVO c) throws Exception {
        facade.updateCustomer(c);
    }

    // ================= RETIREMENT LOGIC =================
    public RetirementPlanVO generateRetirementPlan(CustomerVO c) {

        logger.info("Service: generateRetirementPlan for customer ID="
                + c.getCustomerId());

        RetirementPlanBO bo = new RetirementPlanBO();
        return bo.calculatePlan(c);
    }
}
