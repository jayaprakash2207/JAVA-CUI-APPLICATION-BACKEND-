package RetirementPlanning.bo;

import RetirementPlanning.dao.CustomerDAO;
import RetirementPlanning.dao.RetirementPlanDAO;
import RetirementPlanning.exception.RecordNotFoundException;
import RetirementPlanning.exception.RetirementException;
import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RetirementPlanBO {
    private static final int RETIREMENT_AGE = 60;
    private static final int CORPUS_MULTIPLIER = 20;

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final RetirementPlanDAO planDAO = new RetirementPlanDAO();
    public void registerCustomer(CustomerVO c)
            throws RetirementException {
        validateCustomerForRegistration(c);

        try {
            if (customerDAO.emailExists(c.getEmail())) {
                throw new RetirementException("Duplicate email");
            }customerDAO.insertCustomer(c);
            customerDAO.insertFinancialDetails(c);

        } catch (SQLException e) {
            throw new RetirementException("Registration failed", e);
        }
    }
    public CustomerVO getCustomerById(int id)
            throws RetirementException, RecordNotFoundException {
        try {
            CustomerVO customer = customerDAO.getCustomerById(id);
            if (customer == null) {
                throw new RecordNotFoundException("Customer not found");
            }
            return customer;

        } catch (SQLException e) {
            throw new RetirementException("Fetch is failed", e);
        }
    }public List<CustomerVO> getAllCustomers()
            throws RetirementException {

        try {
            return customerDAO.getAllCustomers();
        } catch (SQLException e) {
            throw new RetirementException("Fetcch failed", e);
        }
    }
    public void updateCustomer(CustomerVO c)
            throws RetirementException {

        validateCustomerForUpdate(c);
        try {
            CustomerVO existing = customerDAO.getCustomerById(c.getCustomerId());

            if (existing == null) {
                throw new RetirementException("Customer not found");
            }

            customerDAO.updateCustomer(c);

        } catch (SQLException e) {
            throw new RetirementException("Update failed", e);
        }
    }
    public RetirementPlanVO generateAndSavePlan(CustomerVO c)
            throws RetirementException {
        RetirementPlanVO plan = calculatePlan(c);
        try {
            planDAO.insertPlan(plan);
        } catch (SQLException e) {
            throw new RetirementException("Plan insert failed", e);
        }

        return plan;
    }
    public RetirementPlanVO calculatePlan(CustomerVO c)
            throws RetirementException {
        validateCustomerForPlan(c);
        int yearsToRetirement = RETIREMENT_AGE - c.getAge();

        double targetCorpus = c.getAnnualExpenditure() * CORPUS_MULTIPLIER;

        double remainingAmount = targetCorpus - c.getCurrentSavings();

        if (remainingAmount < 0) {
            remainingAmount = 0;
        }
        double monthlySavingsRequired = remainingAmount / (yearsToRetirement * 12);
        RetirementPlanVO plan = new RetirementPlanVO();
        plan.setCustomerId(c.getCustomerId());
        plan.setAgeAtCreation(c.getAge());
        plan.setYearsToRetirement(yearsToRetirement);
        plan.setTargetCorpus(targetCorpus);
        plan.setMonthlySavingsRequired(monthlySavingsRequired);
        plan.setRecommendations("");
        return plan;
    }
    private void validateCustomerForRegistration(CustomerVO c)
            throws RetirementException {
        if (c == null) {
            throw new RetirementException("Customer cannot be null");
        }

        validateBasicDetails(c);
        validateFinancialDetails(c);
    }
    private void validateCustomerForUpdate(CustomerVO c)
            throws RetirementException {
        if (c == null) {
            throw new RetirementException("Customer cannot be null");
        }
        if (c.getCustomerId() <= 0) {
            throw new RetirementException("Invalid customer ID");
        }
        validateBasicDetails(c);
    }
    private void validateCustomerForPlan(CustomerVO c)
            throws RetirementException {
        if (c == null) {
            throw new RetirementException("Customer data cannot be null");
        }

        if (c.getAge() < 18) {
            throw new RetirementException("age must be 18 or above");
        }
        if (c.getAge() >= RETIREMENT_AGE) {
            throw new RetirementException("Already at retirement age");
        }

        if (c.getAnnualExpenditure() <= 0) {
            throw new RetirementException("Invalid annual expenditure");
        }
    }
    private void validateBasicDetails(CustomerVO c)
            throws RetirementException {

        if (c.getCustomerName() == null ||
                !c.getCustomerName().matches("[A-Za-z ]{3,}")) {
            throw new RetirementException("name must contain only letters and minimum 3 characterrs");
        }

        if (c.getDob() == null ||
                c.getDob().toLocalDate().isAfter(LocalDate.now())) {
            throw new RetirementException("Date of Birth cannot bee the future date");
        }
        if (c.getAge() < 18) {
            throw new RetirementException("Age must be 18 or above");
        }
        if (c.getPhone() == null ||
                !c.getPhone().matches("\\d{10}")) {
            throw new RetirementException("Phone number must ben 10 digitss");
        }
    }private void validateFinancialDetails(CustomerVO c)
            throws RetirementException {

        if (c.getAnnualIncome() <= 0) {
            throw new RetirementException("Annual income must be positive");
        }
        if (c.getAnnualExpenditure() < 0) {
            throw new RetirementException("Expenditure cannot be negative");
        }
        if (c.getAnnualExpenditure() > c.getAnnualIncome()) {
            throw new RetirementException("Expenditure cannot exceed income");
        }
    }
}
