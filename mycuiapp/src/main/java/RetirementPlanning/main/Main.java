package RetirementPlanning.main;

import RetirementPlanning.service.RetirementService;
import RetirementPlanning.vo.CustomerVO;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

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
                5. Delete Customer by ID
                6. Delete All Customers
                7. Exit
                -----------------------------
                """);

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (ch) {

                // ================= ADD CUSTOMER =================
                case 1 -> {
                    logger.info("UI Action: Add Customer requested");

                    CustomerVO c = new CustomerVO();

                    System.out.print("Name: ");
                    c.setCustomerName(sc.nextLine());

                    System.out.print("DOB (yyyy-mm-dd): ");
                    c.setDob(Date.valueOf(sc.nextLine()));

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
                    sc.nextLine();

                    service.addCustomer(c);

                    logger.info("UI Action: Customer added successfully, ID=" + c.getCustomerId());

                    System.out.println("✔ Customer added successfully");
                    System.out.println("Generated Customer ID: " + c.getCustomerId());
                }

                // ================= VIEW BY ID =================
                case 2 -> {
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    logger.info("UI Action: View Customer requested, ID=" + id);

                    CustomerVO c = service.viewCustomer(id);

                    if (c != null) {
                        System.out.println("""
                            -----------------------------
                            ID      : %d
                            Name    : %s
                            Age     : %d
                            Email   : %s
                            Phone   : %s
                            Address : %s
                            Income  : %.2f
                            Savings : %.2f
                            -----------------------------
                            """.formatted(
                                c.getCustomerId(),
                                c.getCustomerName(),
                                c.getAge(),
                                c.getEmail(),
                                c.getPhone(),
                                c.getAddress(),
                                c.getAnnualIncome(),
                                c.getCurrentSavings()
                        ));
                    } else {
                        logger.warn("UI Action: Customer not found, ID=" + id);
                        System.out.println("❌ Customer not found");
                    }
                }

                // ================= VIEW ALL =================
                case 3 -> {
                    logger.info("UI Action: Fetch All Customers requested");

                    List<CustomerVO> list = service.viewAllCustomers();

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

                    logger.info("UI Action: Update Customer requested, ID=" + c.getCustomerId());

                    System.out.print("Name: ");
                    c.setCustomerName(sc.nextLine());

                    System.out.print("DOB (yyyy-mm-dd): ");
                    c.setDob(Date.valueOf(sc.nextLine()));

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
                    sc.nextLine();

                    service.updateCustomer(c);

                    logger.info("UI Action: Customer updated successfully, ID=" + c.getCustomerId());
                    System.out.println("✔ Customer updated successfully");
                }

                // ================= DELETE BY ID =================
                case 5 -> {
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    logger.info("UI Action: Delete Customer requested, ID=" + id);

                    service.deleteCustomer(id);

                    logger.info("UI Action: Customer deleted successfully, ID=" + id);
                    System.out.println("✔ Customer deleted successfully");
                }

                // ================= DELETE ALL =================
                case 6 -> {
                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {
                        logger.warn("UI Action: Delete ALL Customers requested");
                        service.deleteAllCustomers();
                        System.out.println("✔ All customers deleted");
                    } else {
                        logger.info("UI Action: Delete ALL cancelled");
                        System.out.println("Operation cancelled");
                    }
                }

                // ================= EXIT =================
                case 7 -> {
                    logger.info("Application exited");
                    sc.close();
                    return;
                }

                default -> System.out.println("❌ Invalid choice");
            }
        }
    }
}
