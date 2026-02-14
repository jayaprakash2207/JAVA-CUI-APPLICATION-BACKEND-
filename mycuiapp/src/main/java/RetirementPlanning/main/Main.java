package RetirementPlanning.main;

import RetirementPlanning.service.RetirementService;
import RetirementPlanning.vo.CustomerVO;
import RetirementPlanning.vo.RetirementPlanVO;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Application started");

        Scanner sc = new Scanner(System.in);
        RetirementService service = new RetirementService();

        while (true) {
            System.out.println("""
                ----------------__
                1. Add Customer
                2. View Customer by ID
                3. View All Customers
                4. Update Customer
                5. Exit
               """);

            System.out.print("Enter choice: ");
            int ch;
            try {
                ch = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    logger.info("UI Action: Add Customer");

                    CustomerVO c = new CustomerVO();
                    System.out.print("Name: ");
                    c.setCustomerName(sc.nextLine());

                    System.out.print("DOB (yyyy-mm-dd): ");
                    try {
                        c.setDob(Date.valueOf(sc.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Invalid date format");
                        break;
                    }
                    System.out.print("Age: ");
                    c.setAge(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Email: ");
                    c.setEmail(sc.nextLine());

                    System.out.print("Phone: ");
                    c.setPhone(sc.nextLine());
                    System.out.print("Address: ");
                    c.setAddress(sc.nextLine());

                    System.out.print("Annual Income: ");
                    c.setAnnualIncome(sc.nextDouble());
                    System.out.print("Annual Expenditure: ");
                    c.setAnnualExpenditure(sc.nextDouble());
                    System.out.print("Current Savings: ");
                    c.setCurrentSavings(sc.nextDouble());

                    System.out.print("Total EMIs: ");
                    c.setTotalEmis(sc.nextDouble());
                    sc.nextLine();

                    service.addCustomer(c);

                    if (c.getCustomerId() > 0) {
                        RetirementPlanVO plan = service.generateRetirementPlan(c);
                        if (plan != null) {
                            System.out.println("\nRetirement Plan Summary");
//                            System.out.println("-----------");
                            System.out.println("Years to Retirement : " + plan.getYearsToRetirement());
                            System.out.println("Target Corpus       : ₹" + Math.round(plan.getTargetCorpus()));
                            System.out.println("Monthly Savings Req : ₹" + Math.round(plan.getMonthlySavingsRequired()));
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    CustomerVO c = service.viewCustomer(id);

                    if (c != null) {
                        System.out.println("""
                                
                                ID      : %d
                                Name    : %s
                                Age     : %d
                                Email   : %s
                                Phone   : %s
                                
                                """.formatted(
                                c.getCustomerId(),
                                c.getCustomerName(),
                                c.getAge(),
                                c.getEmail(),
                                c.getPhone()));
                    }
                }
                case 3 -> {
                    List<CustomerVO> list =
                            service.viewAllCustomers();

                    if (list.isEmpty()) {
                        System.out.println("No customers found");
                    } else {
                        System.out.println("ID | Name | Age | Email");
                        list.stream()
                                .forEach(c -> System.out.println(
                                        c.getCustomerId() + " | " +
                                                c.getCustomerName() + " | " +
                                                c.getAge() + " | " +
                                                c.getEmail()
                                ));
                    }
                }
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
                    System.out.print("Address: ");
                    c.setAddress(sc.nextLine());

                    service.updateCustomer(c);
                }
                case 5 -> {
                    logger.info("Application exited");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice");
            }
        }
    }
}
