package RetirementPlanning.dao;

import RetirementPlanning.util.DBConnection;
import RetirementPlanning.vo.RetirementPlanVO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RetirementPlanDAO {

    public void insertPlan(RetirementPlanVO p) throws Exception {

        String sql = """
            INSERT INTO Retirement_Plan
            (Customer_Id, Age_At_Creation, Years_To_Retirement,
             Target_Corpus, Monthly_Savings_Required)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getCustomerId());
            ps.setInt(2, p.getAgeAtCreation());
            ps.setInt(3, p.getYearsToRetirement());
            ps.setDouble(4, p.getTargetCorpus());
            ps.setDouble(5, p.getMonthlySavingsRequired());

            ps.executeUpdate();
        }
    }
}
