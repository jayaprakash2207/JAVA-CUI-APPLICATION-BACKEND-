package RetirementPlanning.vo;

public class RetirementPlanVO {

    private int customerId;
    private int ageAtCreation;
    private int yearsToRetirement;
    private double targetCorpus;
    private double monthlySavingsRequired;

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getAgeAtCreation() { return ageAtCreation; }
    public void setAgeAtCreation(int ageAtCreation) { this.ageAtCreation = ageAtCreation; }

    public int getYearsToRetirement() { return yearsToRetirement; }
    public void setYearsToRetirement(int yearsToRetirement) { this.yearsToRetirement = yearsToRetirement; }

    public double getTargetCorpus() { return targetCorpus; }
    public void setTargetCorpus(double targetCorpus) { this.targetCorpus = targetCorpus; }

    public double getMonthlySavingsRequired() { return monthlySavingsRequired; }
    public void setMonthlySavingsRequired(double monthlySavingsRequired) {
        this.monthlySavingsRequired = monthlySavingsRequired;
    }
}
