package RetirementPlanning.bo;

import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;
import org.apache.log4j.Logger;

public class RetirementPlanBO {

    private static final Logger logger =
            Logger.getLogger(RetirementPlanBO.class);

    public RetirementPlanVO calculatePlan(CustomerVO c) {

        logger.info("Retirement calculation started for customer ID="
                + c.getCustomerId());

        int retirementAge = 60;
        int yearsLeft = retirementAge - c.getAge();

        double targetCorpus =
                c.getAnnualExpenditure() * 20;

        double monthlySaving =
                (targetCorpus - c.getCurrentSavings())
                        / (yearsLeft * 12);

        RetirementPlanVO plan = new RetirementPlanVO();
        plan.setCustomerId(c.getCustomerId());
        plan.setAgeAtCreation(c.getAge());
        plan.setYearsToRetirement(yearsLeft);
        plan.setTargetCorpus(targetCorpus);
        plan.setMonthlySavingsRequired(monthlySaving);

        plan.setRecommendations(
                "Mutual Funds, Pension Plans, Fixed Deposits, Insurance-linked products"
        );

        logger.info("Retirement plan calculated: TargetCorpus="
                + targetCorpus + ", MonthlySaving=" + monthlySaving);

        return plan;
    }
}
