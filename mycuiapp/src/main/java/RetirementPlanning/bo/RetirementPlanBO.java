package RetirementPlanning.bo;

import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;

public class RetirementPlanBO {

    public RetirementPlanVO calculatePlan(int customerId,
                                          int age,
                                          CustomerVO c) {

        int yearsLeft = 60 - age;
        double targetCorpus = c.getAnnualExpenditure() * 20;

        double monthlySaving =
                (targetCorpus - c.getCurrentSavings())
                        / (yearsLeft * 12);

        RetirementPlanVO plan = new RetirementPlanVO();
        plan.setCustomerId(customerId);
        plan.setAgeAtCreation(age);
        plan.setYearsToRetirement(yearsLeft);
        plan.setTargetCorpus(targetCorpus);
        plan.setMonthlySavingsRequired(monthlySaving);

        return plan;
    }
}
