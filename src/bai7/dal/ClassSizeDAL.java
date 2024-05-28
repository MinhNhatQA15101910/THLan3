package bai7.dal;

import bai7.dto.models.ClassSizeDTO;
import bai7.dto.responses.ClassSizeListResponseDTO;
import bai7.dto.responses.ClassSizeResponseDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassSizeDAL implements IClassSizeDAL {
    @Override
    public ClassSizeListResponseDTO getAllClassSize() {
        List<ClassSizeDTO> classSizeList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery(
                    """
                            SELECT l.MaLop, COUNT(sv.MaSV) AS SoLuongSinhVien\s
                            FROM LOP l
                            LEFT JOIN SinhVien sv ON l.MaLop = sv.Lop
                            GROUP BY l.MaLop;
                            """
            );

            while (rsData.next()) {
                classSizeList.add(
                        new ClassSizeDTO(
                                rsData.getString("MaLop"),
                                rsData.getInt("SoLuongSinhVien")
                        )
                );
            }

            return new ClassSizeListResponseDTO(200, "", classSizeList);
        } catch (SQLException e) {
            return new ClassSizeListResponseDTO(500, e.getMessage(), null);
        }
    }

    @Override
    public ClassSizeResponseDTO getClassWithMaxNumberOfStudents() {
        ClassSizeDTO classSize = new ClassSizeDTO("", 0);
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery(
                    """
                            SELECT TOP 1 Lop, COUNT(*) AS SoLuongSinhVien\s
                            FROM SinhVien\s
                            GROUP BY Lop\s
                            ORDER BY SoLuongSinhVien DESC;
                            """
            );

            while (rsData.next()) {
                classSize = new ClassSizeDTO(
                        rsData.getString("Lop"),
                        rsData.getInt("SoLuongSinhVien")
                );
            }

            return new ClassSizeResponseDTO(200, "", classSize);
        } catch (SQLException e) {
            return new ClassSizeResponseDTO(500, e.getMessage(), null);
        }
    }

    @Override
    public ClassSizeResponseDTO getClassWithMaxNumberOfStudentsFailed() {
        ClassSizeDTO classSize = new ClassSizeDTO("", 0);
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery(
                    """
                            SELECT TOP 1 Lop, COUNT(*) AS SoLuongKhongDat\s
                            FROM SinhVien\s
                            WHERE DiemTB < 5\s
                            GROUP BY Lop\s
                            ORDER BY SoLuongKhongDat DESC;
                            """
            );

            while (rsData.next()) {
                classSize = new ClassSizeDTO(
                        rsData.getString("Lop"),
                        rsData.getInt("SoLuongKhongDat")
                );
            }

            return new ClassSizeResponseDTO(200, "", classSize);
        } catch (SQLException e) {
            return new ClassSizeResponseDTO(500, e.getMessage(), null);
        }
    }
}
