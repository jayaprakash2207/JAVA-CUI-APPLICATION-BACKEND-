package RetirementPlanning.dao;

import RetirementPlanning.util.DBConnection;
import RetirementPlanning.vo.CustomerVO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private static final Logger logger =
            Logger.getLogger(CustomerDAO.class);

    public void insertCustomer(CustomerVO c) throws Exception {

        logger.info("DAO: Customer input → " + c);

        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try {
            String sql = """
                INSERT INTO Customer
                (Customer_Name, DOB, Age, Email, Phone, Address)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement ps = con.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);

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

            con.commit();
            logger.info("DAO: Customer saved → " + c);

        } catch (Exception e) {
            con.rollback();
            logger.error("DAO error", e);
            throw e;
        } finally {
            con.close();
        }
    }

    public CustomerVO getCustomerById(int id) throws Exception {
        String sql = "SELECT * FROM Customer WHERE Customer_Id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CustomerVO c = new CustomerVO();
                c.setCustomerId(id);
                c.setCustomerName(rs.getString("Customer_Name"));
                c.setAge(rs.getInt("Age"));
                c.setEmail(rs.getString("Email"));
                c.setPhone(rs.getString("Phone"));
                return c;
            }
        }
        return null;
    }

    public List<CustomerVO> getAllCustomers() throws Exception {
        List<CustomerVO> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CustomerVO c = new CustomerVO();
                c.setCustomerId(rs.getInt("Customer_Id"));
                c.setCustomerName(rs.getString("Customer_Name"));
                c.setAge(rs.getInt("Age"));
                c.setEmail(rs.getString("Email"));
                list.add(c);
            }
        }
        return list;
    }

    public void updateCustomer(CustomerVO c) throws Exception {
        String sql = """
            UPDATE Customer
            SET Customer_Name=?, Age=?, Email=?, Phone=?, Address=?
            WHERE Customer_Id=?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCustomerName());
            ps.setInt(2, c.getAge());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getAddress());
            ps.setInt(6, c.getCustomerId());
            ps.executeUpdate();
        }
    }
}
