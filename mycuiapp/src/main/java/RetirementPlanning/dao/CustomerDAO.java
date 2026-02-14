package RetirementPlanning.dao;

import RetirementPlanning.util.DBConnection;
import RetirementPlanning.vo.CustomerVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public boolean emailExists(String email) throws SQLException {

        String sql = "select count(*) from customer where email=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public boolean phoneExists(String phone) throws SQLException {

        String sql = "select count(*) from customer where phone=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public void insertCustomer(CustomerVO c) throws SQLException {

        String sql = """
            insert into customer
            (customer_name, dob, age, email, phone, address)
            values (?, ?, ?, ?, ?, ?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
        }
    }


    public CustomerVO getCustomerById(int id) throws SQLException {

        String sql = "select * from customer where customer_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }

            CustomerVO c = new CustomerVO();
            c.setCustomerId(rs.getInt("customer_id"));
            c.setCustomerName(rs.getString("customer_name"));
            c.setAge(rs.getInt("age"));
            c.setEmail(rs.getString("email"));
            c.setPhone(rs.getString("phone"));
            c.setAddress(rs.getString("address"));
            c.setDob(rs.getDate("dob"));

            return c;
        }
    }

    public List<CustomerVO> getAllCustomers() throws SQLException {

        List<CustomerVO> list = new ArrayList<>();

        String sql = "select * from customer";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                CustomerVO c = new CustomerVO();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setCustomerName(rs.getString("customer_name"));
                c.setAge(rs.getInt("age"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setDob(rs.getDate("dob"));

                list.add(c);
            }
        }
        return list;
    }

    public void insertFinancialDetails(CustomerVO c) throws SQLException {

        String sql = """
            insert into customer_financials
            (customer_id, annual_income, annual_expenditure,
             current_savings, total_emis)
            values (?, ?, ?, ?, ?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getCustomerId());
            ps.setDouble(2, c.getAnnualIncome());
            ps.setDouble(3, c.getAnnualExpenditure());
            ps.setDouble(4, c.getCurrentSavings());
            ps.setDouble(5, c.getTotalEmis());

            ps.executeUpdate();
        }
    }

    public void updateCustomer(CustomerVO c) throws SQLException {

        String sql = """ 
update customer set customer_name=?, age=?, email=?, phone=?, address=? where customer_id=? """;

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
