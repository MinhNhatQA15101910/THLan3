package bai2.dal;

import bai2.dto.TableDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAL implements ITableDAL {

    @Override
    public List<TableDTO> getAllTables() {
        List<TableDTO> tableList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rsTables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (rsTables.next()) {
                tableList.add(
                        new TableDTO(
                                rsTables.getString("TABLE_NAME")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableList;
    }
}
