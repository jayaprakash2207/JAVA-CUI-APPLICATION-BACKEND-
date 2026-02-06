package RetirementPlanning.dao;

import RetirementPlanning.util.DBConnection;
import RetirementPlanning.vo.CustomerVO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static final Logger logger = Logger.getLogger(CustomerDAO.class);

    // ================= ADD CUSTOMER =================
    public void insertCustomer(CustomerVO c) throws Exception {

        logger.info("DAO: insertCustomer started");
        logger.info("DAO: Customer input data → " + c);

        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            String customerSql = """
                INSERT INTO Customer
                (Customer_Name, DOB, Age, Email, Phone, Address)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement ps = con.prepareStatement(
                    customerSql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getCustomerName());
            ps.setDate(2, c.getDob());
            ps.setInt(3, c.getAge());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPhone());
            ps.setString(6, c.getAddress());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setCustomerId(rs.getInt(1));
            }

            String financeSql = """
                INSERT INTO Customer_Financials
                (Customer_Id, Annual_Income, Annual_Expenditure,
                 Current_Savings, Total_EMIs)
                VALUES (?, ?, ?, ?, ?)
            """;

            PreparedStatement ps2 = con.prepareStatement(financeSql);
            ps2.setInt(1, c.getCustomerId());
            ps2.setDouble(2, c.getAnnualIncome());
            ps2.setDouble(3, c.getAnnualExpenditure());
            ps2.setDouble(4, c.getCurrentSavings());
            ps2.setDouble(5, 0);
            ps2.executeUpdate();

            con.commit();
            logger.info("DAO: Customer successfully saved → " + c);

        } catch (Exception e) {
            logger.error("DAO: insertCustomer failed", e);
            con.rollback();
            throw e;
        } finally {
            con.close();
            logger.info("DAO: DB connection closed");
        }
    }

    // ================= FETCH BY ID =================
    public CustomerVO getCustomerById(int id) throws Exception {

        logger.info("DAO: Fetching customer by ID = " + id);

        String sql = """
            SELECT c.*, f.Annual_Income, f.Annual_Expenditure, f.Current_Savings
            FROM Customer c
            JOIN Customer_Financials f
            ON c.Customer_Id = f.Customer_Id
            WHERE c.Customer_Id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CustomerVO c = new CustomerVO();
                c.setCustomerId(rs.getInt("Customer_Id"));
                c.setCustomerName(rs.getString("Customer_Name"));
                c.setDob(rs.getDate("DOB"));
                c.setAge(rs.getInt("Age"));
                c.setEmail(rs.getString("Email"));
                c.setPhone(rs.getString("Phone"));
                c.setAddress(rs.getString("Address"));
                c.setAnnualIncome(rs.getDouble("Annual_Income"));
                c.setAnnualExpenditure(rs.getDouble("Annual_Expenditure"));
                c.setCurrentSavings(rs.getDouble("Current_Savings"));

                logger.info("DAO: Customer fetched → " + c);
                return c;
            } else {
                logger.warn("DAO: No customer found for ID = " + id);
            }
        }
        return null;
    }

    // ================= FETCH ALL =================
    public List<CustomerVO> getAllCustomers() throws Exception {

        logger.info("DAO: Fetching ALL customers");

        List<CustomerVO> list = new ArrayList<>();

        String sql = """
            SELECT c.*, f.Annual_Income, f.Annual_Expenditure, f.Current_Savings
            FROM Customer c
            JOIN Customer_Financials f
            ON c.Customer_Id = f.Customer_Id
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CustomerVO c = new CustomerVO();
                c.setCustomerId(rs.getInt("Customer_Id"));
                c.setCustomerName(rs.getString("Customer_Name"));
                c.setAge(rs.getInt("Age"));
                c.setEmail(rs.getString("Email"));
                c.setAnnualIncome(rs.getDouble("Annual_Income"));
                c.setCurrentSavings(rs.getDouble("Current_Savings"));
                list.add(c);
            }
        }

        logger.info("DAO: Total customers fetched = " + list.size());
        list.forEach(c ->
                logger.info("DAO: Customer record → " + c)
        );

        return list;
    }

    // ================= UPDATE =================
    public void updateCustomer(CustomerVO c) throws Exception {

        logger.info("DAO: Updating customer (NEW DATA) → " + c);

        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            String customerSql = """
                UPDATE Customer
                SET Customer_Name=?, DOB=?, Age=?, Email=?, Phone=?, Address=?
                WHERE Customer_Id=?
            """;

            PreparedStatement ps1 = con.prepareStatement(customerSql);
            ps1.setString(1, c.getCustomerName());
            ps1.setDate(2, c.getDob());
            ps1.setInt(3, c.getAge());
            ps1.setString(4, c.getEmail());
            ps1.setString(5, c.getPhone());
            ps1.setString(6, c.getAddress());
            ps1.setInt(7, c.getCustomerId());
            ps1.executeUpdate();

            String financeSql = """
                UPDATE Customer_Financials
                SET Annual_Income=?, Annual_Expenditure=?, Current_Savings=?
                WHERE Customer_Id=?
            """;

            PreparedStatement ps2 = con.prepareStatement(financeSql);
            ps2.setDouble(1, c.getAnnualIncome());
            ps2.setDouble(2, c.getAnnualExpenditure());
            ps2.setDouble(3, c.getCurrentSavings());
            ps2.setInt(4, c.getCustomerId());
            ps2.executeUpdate();

            con.commit();
            logger.info("DAO: Customer update committed → ID=" + c.getCustomerId());

        } catch (Exception e) {
            logger.error("DAO: Update failed", e);
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }

    // ================= DELETE =================
    public void deleteCustomer(int id) throws Exception {
        logger.warn("DAO: Deleting customer with ID = " + id);
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("DELETE FROM Customer WHERE Customer_Id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.warn("DAO: Customer deleted with ID = " + id);
        }
    }

    public void deleteAllCustomers() throws Exception {
        logger.warn("DAO: DELETE ALL triggered");
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("DELETE FROM Customer")) {
            ps.executeUpdate();
            logger.warn("DAO: ALL customer records deleted");
        }
    }
}
