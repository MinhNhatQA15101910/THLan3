package bai6.dal;

import bai5.dal.Utils;
import bai6.dto.models.StoredProcedureDTO;
import bai6.dto.responses.StoredProcedureListResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoredProcedureDAL implements IStoredProcedureDAL {
    @Override
    public StoredProcedureListResponse getAllStoredProcedures() {
        List<StoredProcedureDTO> storedProcedureList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery("""
                    SELECT name, create_date, modify_date\s
                    FROM sys.procedures\s
                    ORDER BY name;"""
            );

            while (rsData.next()) {
                storedProcedureList.add(
                        new StoredProcedureDTO(
                                rsData.getString("name"),
                                rsData.getDate("create_date"),
                                rsData.getDate("modify_date")
                        )
                );
            }

            return new StoredProcedureListResponse(200, "", storedProcedureList);
        } catch (SQLException e) {
            return new StoredProcedureListResponse(500, e.getMessage(), null);
        }
    }
}
