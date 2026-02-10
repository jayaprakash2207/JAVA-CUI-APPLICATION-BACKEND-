package RetirementPlanning.vo;

import java.sql.Date;

public class CustomerVO {

    private int customerId;
    private String customerName;
    private Date dob;
    private int age;
    private String email;
    private String phone;
    private String address;

    private double annualIncome;
    private double annualExpenditure;
    private double currentSavings;

    // Getters & Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(double annualIncome) { this.annualIncome = annualIncome; }

    public double getAnnualExpenditure() { return annualExpenditure; }
    public void setAnnualExpenditure(double annualExpenditure) { this.annualExpenditure = annualExpenditure; }

    public double getCurrentSavings() { return currentSavings; }
    public void setCurrentSavings(double currentSavings) { this.currentSavings = currentSavings; }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "id=" + customerId +
                ", name='" + customerName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", income=" + annualIncome +
                ", savings=" + currentSavings +
                '}';
    }
}
