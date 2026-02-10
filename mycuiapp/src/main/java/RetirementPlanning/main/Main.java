package RetirementPlanning.main;

import RetirementPlanning.service.RetirementService;
import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger =
            Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        logger.info("Application started");

        Scanner sc = new Scanner(System.in);
        RetirementService service = new RetirementService();

        while (true) {

            System.out.println("""
                -----------------------------
                1. Add Customer
                2. View Customer by ID
                3. View All Customers
                4. Update Customer
                5. Exit
                -----------------------------
                """);

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                // ================= ADD CUSTOMER =================
                case 1 -> {
                    logger.info("UI Action: Add Customer requested");

                    CustomerVO c = new CustomerVO();

                    System.out.print("Name: ");
                    c.setCustomerName(sc.nextLine());

                    if (!service.isValidName(c.getCustomerName())) {
                        System.out.println("❌ Invalid name");
                        break;
                    }

                    System.out.print("DOB (yyyy-mm-dd): ");
                    c.setDob(Date.valueOf(sc.nextLine()));

                    if (!service.isValidDOB(c.getDob())) {
                        System.out.println("❌ DOB cannot be future date");
                        break;
                    }

                    System.out.print("Age: ");
                    c.setAge(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Email: ");
                    c.setEmail(sc.nextLine());

                    System.out.print("Phone: ");
                    c.setPhone(sc.nextLine());

                    if (!service.isValidPhone(c.getPhone())) {
                        System.out.println("❌ Invalid phone number");
                        break;
                    }

                    System.out.print("Address: ");
                    c.setAddress(sc.nextLine());

                    System.out.print("Annual Income: ");
                    c.setAnnualIncome(sc.nextDouble());

                    System.out.print("Annual Expenditure: ");
                    c.setAnnualExpenditure(sc.nextDouble());

                    System.out.print("Current Savings: ");
                    c.setCurrentSavings(sc.nextDouble());
                    sc.nextLine();

                    // Save customer
                    service.addCustomer(c);

                    logger.info("Customer added successfully, ID=" + c.getCustomerId());

                    // ===== RETIREMENT PLAN GENERATION =====
                    RetirementPlanVO plan =
                            service.generateRetirementPlan(c);

                    System.out.println("\n📊 Retirement Plan Summary");
                    System.out.println("-----------------------------");
                    System.out.println("Years to Retirement : " +
                            plan.getYearsToRetirement());
                    System.out.println("Target Corpus       : ₹" +
                            Math.round(plan.getTargetCorpus()));
                    System.out.println("Monthly Savings Req : ₹" +
                            Math.round(plan.getMonthlySavingsRequired()));
                    System.out.println("Recommended Options : " +
                            plan.getRecommendations());
                    System.out.println("-----------------------------");

                    System.out.println("✔ Customer added successfully");
                    System.out.println("Generated Customer ID: " +
                            c.getCustomerId());
                }

                // ================= VIEW BY ID =================
                case 2 -> {
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    CustomerVO c = service.viewCustomer(id);

                    if (c != null) {
                        System.out.println("""
                            -----------------------------
                            ID      : %d
                            Name    : %s
                            Age     : %d
                            Email   : %s
                            Phone   : %s
                            -----------------------------
                            """.formatted(
                                c.getCustomerId(),
                                c.getCustomerName(),
                                c.getAge(),
                                c.getEmail(),
                                c.getPhone()
                        ));
                    } else {
                        System.out.println("❌ Customer not found");
                    }
                }

                // ================= VIEW ALL =================
                case 3 -> {
                    List<CustomerVO> list =
                            service.viewAllCustomers();

                    if (list.isEmpty()) {
                        System.out.println("No customers found");
                    } else {
                        System.out.println("ID | Name | Age | Email");
                        System.out.println("----------------------------------");
                        for (CustomerVO c : list) {
                            System.out.println(
                                    c.getCustomerId() + " | " +
                                            c.getCustomerName() + " | " +
                                            c.getAge() + " | " +
                                            c.getEmail()
                            );
                        }
                    }
                }

                // ================= UPDATE =================
                case 4 -> {
                    CustomerVO c = new CustomerVO();

                    System.out.print("Customer ID: ");
                    c.setCustomerId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Name: ");
                    c.setCustomerName(sc.nextLine());

                    System.out.print("Age: ");
                    c.setAge(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Email: ");
                    c.setEmail(sc.nextLine());

                    System.out.print("Phone: ");
                    c.setPhone(sc.nextLine());

                    service.updateCustomer(c);

                    System.out.println("✔ Customer updated successfully");
                }

                // ================= EXIT =================
                case 5 -> {
                    logger.info("Application exited");
                    sc.close();
                    return;
                }

                default -> System.out.println("❌ Invalid choice");
            }
        }
    }
}
