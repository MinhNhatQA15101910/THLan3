package bai3.dal;

import bai3.dto.models.LopDTO;
import bai3.dto.responses.MessageDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDAL implements IClassDAL {
    @Override
    public MessageDTO addClass(LopDTO classObj) {
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Lop VALUES (?, ?, ?)")) {
                pstmt.setString(1, classObj.maLop());
                pstmt.setString(2, classObj.tenLop());
                pstmt.setString(3, classObj.cvht());

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    return new MessageDTO(200, "Thêm lớp thành công.");
                }

                return new MessageDTO(500, "Đã có lỗi xảy ra.");
            }
        } catch (SQLException e) {
            return new MessageDTO(500, e.getMessage());
        }
    }

    @Override
    public MessageDTO deleteClass(String maLop) {
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Lop WHERE MaLop = ?")) {
                pstmt.setString(1, maLop);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    return new MessageDTO(200, "Xóa lớp thành công.");
                }

                return new MessageDTO(500, "Đã có lỗi xảy ra.");
            }
        } catch (SQLException e) {
            return new MessageDTO(500, e.getMessage());
        }
    }

    @Override
    public LopDTO getClassById(String maLop) {
        LopDTO classObj = new LopDTO("", "", "");
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Lop WHERE MaLop = ?")) {
                pstmt.setString(1, maLop);

                ResultSet rsData = pstmt.executeQuery();

                while (rsData.next()) {
                    classObj = new LopDTO(
                            rsData.getString("MaLop"),
                            rsData.getString("TenLop"),
                            rsData.getString("CVHT")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classObj;
    }

    @Override
    public LopDTO getClassByName(String tenLop) {
        LopDTO classObj = new LopDTO("", "", "");
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Lop WHERE TenLop = ?")) {
                pstmt.setString(1, tenLop);

                ResultSet rsData = pstmt.executeQuery();

                while (rsData.next()) {
                    classObj = new LopDTO(
                            rsData.getString("MaLop"),
                            rsData.getString("TenLop"),
                            rsData.getString("CVHT")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classObj;
    }

    @Override
    public List<LopDTO> getAllClasses() {
        List<LopDTO> classList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rsData = stmt.executeQuery("SELECT * FROM Lop");

            while (rsData.next()) {
                classList.add(
                        new LopDTO(
                                rsData.getString("MaLop"),
                                rsData.getString("TenLop"),
                                rsData.getString("CVHT")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classList;
    }

    @Override
    public MessageDTO updateClass(LopDTO classObj) {
        try (Connection conn = DriverManager.getConnection(Utils.CONNECTION_URL)) {
            try (PreparedStatement pstmt = conn.prepareStatement("UPDATE Lop SET TenLop = ?, CVHT = ? WHERE MaLop = ?")) {
                pstmt.setString(1, classObj.tenLop());
                pstmt.setString(2, classObj.cvht());
                pstmt.setString(3, classObj.maLop());

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    return new MessageDTO(200, "Cập nhật lớp thành công.");
                }

                return new MessageDTO(500, "Đã có lỗi xảy ra.");
            }
        } catch (SQLException e) {
            return new MessageDTO(500, e.getMessage());
        }
    }
}
