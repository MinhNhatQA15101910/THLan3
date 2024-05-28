package bai6.dal;

import bai5.dal.Utils;
import bai6.dto.models.ConstraintDTO;
import bai6.dto.responses.ConstraintListResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConstraintDAL implements IConstraintDAL {
    @Override
    public ConstraintListResponse getAllConstraints() {
        List<ConstraintDTO> constraintList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery("""
                    SELECT CONSTRAINT_NAME, TABLE_NAME, CONSTRAINT_TYPE\s
                    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS\s
                    WHERE CONSTRAINT_TYPE IN ('PRIMARY KEY', 'FOREIGN KEY', 'UNIQUE', 'CHECK');"""
            );

            while (rsData.next()) {
                constraintList.add(
                        new ConstraintDTO(
                                rsData.getString("CONSTRAINT_NAME"),
                                rsData.getString("TABLE_NAME"),
                                rsData.getString("CONSTRAINT_TYPE")
                        )
                );
            }

            return new ConstraintListResponse(200, "", constraintList);
        } catch (SQLException e) {
            return new ConstraintListResponse(500, e.getMessage(), null);
        }
    }
}
