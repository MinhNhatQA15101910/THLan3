package bai7.dal;

import bai7.dto.models.ClassMaxAvgScoreDTO;
import bai7.dto.responses.ClassMaxAvgScoreResponseDTO;

import java.sql.*;

public class ClassMaxAvgScoreDAL implements IClassMaxAvgScoreDAL {
    @Override
    public ClassMaxAvgScoreResponseDTO getClassWithMaxAvgScoreStudent() {
        ClassMaxAvgScoreDTO classMaxAvgScore = new ClassMaxAvgScoreDTO("", "", 0);
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery(
                    """
                            SELECT TOP 1 Lop, HoTen, DiemTB
                            FROM SinhVien\s
                            GROUP BY Lop, HoTen, DiemTB
                            ORDER BY DiemTB DESC;
                            """
            );

            while (rsData.next()) {
                classMaxAvgScore = new ClassMaxAvgScoreDTO(
                        rsData.getString("Lop"),
                        rsData.getString("HoTen"),
                        rsData.getFloat("DiemTB")
                );
            }

            return new ClassMaxAvgScoreResponseDTO(200, "", classMaxAvgScore);
        } catch (SQLException e) {
            return new ClassMaxAvgScoreResponseDTO(500, e.getMessage(), null);
        }
    }
}
