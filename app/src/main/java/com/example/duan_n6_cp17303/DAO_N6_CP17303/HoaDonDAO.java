package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.HoaDonDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.QLKHDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    Connection objConn;

    public HoaDonDAO() {
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<HoaDonDTO> getAll() {
        List<HoaDonDTO> listCat = new ArrayList<HoaDonDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM HOADON ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    HoaDonDTO hoaDonDTO = new HoaDonDTO();
                    hoaDonDTO.setIdhoadon(resultSet.getInt("ID"));
                    hoaDonDTO.setIdkhachhang(resultSet.getInt("IDKHACHHANG"));
                    hoaDonDTO.setNgaymua(resultSet.getString("NGAYMUA"));
                    hoaDonDTO.setTrangthai(resultSet.getString("TRANGTHAI"));


                    listCat.add(hoaDonDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public void insertRow(HoaDonDTO hoaDonDTO) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO HOADON(NGAYMUA,TRANGTHAI) VALUES (N'" + hoaDonDTO.getNgaymua() + "',N'" + hoaDonDTO.getTrangthai() + "')";

                String generatedColumns[] = {"ID"};

                PreparedStatement stmtInsert = this.objConn.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();

                Log.d("zzzzz", "insertRow: finish insert");
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()) {
                    long id = rs.getLong(1);
                    Log.d("zzzz", "insertRow: ID = " + id);
                }

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu ");
            e.printStackTrace();
        }
    }

    public void updateRow(HoaDonDTO hoaDonDTO) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE HOADON SET name= N'" + hoaDonDTO.getNgaymua() + "',N'" + hoaDonDTO.getTrangthai() + "'WHERE id = " + hoaDonDTO.getIdhoadon();


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
        }
    }

    public int getSLDH(String ngaybatdau, String ngayketthuc) {
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT COUNT(b.SOLUONG) as 'soluongdonhang' FROM HOADON a inner join CHITIETHOADON b on a.ID = b.IDSANPHAM where a.NGAYMUA BETWEEN '" + ngaybatdau + "' AND '" + ngayketthuc + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("soluongdonhang");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return soluong;
    }

    public int getDTTK(String ngaybatdau, String ngayketthuc) {
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'tongdoanhthu' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '" + ngaybatdau + "' AND '" + ngayketthuc + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("tongdoanhthu");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return soluong;
    }

    public long getDT() {
        long soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select SUM(b.SOLUONG*b.TONGTIEN) as 'doanhthu' FROM HOADON a  inner join CHITIETHOADON b on a.ID  = b.IDHOADON where a.TRANGTHAI like N'Đã Giao'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthu");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return soluong;
    }

    public int getSLDH() {
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select COUNT(b.SOLUONG) as 'soluongdonhang' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDHOADON";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("soluongdonhang");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return soluong;
    }

    public List<QLKHDTO> getDonHang() {
        List<QLKHDTO> listCat = new ArrayList<QLKHDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "select HOADON.ID,HOADON.NGAYMUA,HOADON.TRANGTHAI,CHITIETHOADON.TONGTIEN,CHITIETHOADON.SOLUONG,KHACHHANG.TENKHACHHANG,SANPHAM.TENSANPHAM,HOADON.ANHSANPHAM\n" +
                        "from CHITIETHOADON inner join SANPHAM on CHITIETHOADON.IDSANPHAM = SANPHAM.ID\n" +
                        "inner join HOADON\n" +
                        "on HOADON.ID = CHITIETHOADON.IDHOADON\n" +
                        "inner join KHACHHANG\n" +
                        "on HOADON.IDKHACHHANG = KHACHHANG.ID";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    QLKHDTO qlkhdto = new QLKHDTO();
                    qlkhdto.setId(resultSet.getInt("ID"));
                    qlkhdto.setTongtien(resultSet.getFloat("TONGTIEN"));
                    qlkhdto.setNgaymua(resultSet.getString("NGAYMUA"));
                    qlkhdto.setTrangthai(resultSet.getString("TRANGTHAI"));
                    qlkhdto.setSoluong(resultSet.getInt("SOLUONG"));
                    qlkhdto.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    qlkhdto.setTensanpham(resultSet.getString("TENSANPHAM"));
                    qlkhdto.setAnhsanpham(resultSet.getString("ANHSANPHAM"));


                    listCat.add(qlkhdto);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public List<QLKHDTO> getDonHangDG() {
        List<QLKHDTO> listCat = new ArrayList<QLKHDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "select HOADON.ID,HOADON.NGAYMUA,HOADON.TRANGTHAI,CHITIETHOADON.TONGTIEN,CHITIETHOADON.SOLUONG,KHACHHANG.TENKHACHHANG,SANPHAM.TENSANPHAM,HOADON.ANHSANPHAM\n" +
                        "from CHITIETHOADON inner join SANPHAM on CHITIETHOADON.IDSANPHAM = SANPHAM.ID\n" +
                        "inner join HOADON\n" +
                        "on HOADON.ID = CHITIETHOADON.IDHOADON\n" +
                        "inner join KHACHHANG\n" +
                        "on HOADON.IDKHACHHANG = KHACHHANG.ID where HOADON.TRANGTHAI like N'Đã giao'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    QLKHDTO qlkhdto = new QLKHDTO();
                    qlkhdto.setId(resultSet.getInt("ID"));
                    qlkhdto.setTongtien(resultSet.getFloat("TONGTIEN"));
                    qlkhdto.setNgaymua(resultSet.getString("NGAYMUA"));
                    qlkhdto.setTrangthai(resultSet.getString("TRANGTHAI"));
                    qlkhdto.setSoluong(resultSet.getInt("SOLUONG"));
                    qlkhdto.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    qlkhdto.setTensanpham(resultSet.getString("TENSANPHAM"));
                    qlkhdto.setAnhsanpham(resultSet.getString("ANHSANPHAM"));


                    listCat.add(qlkhdto);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public List<QLKHDTO> getDonHangCG() {
        List<QLKHDTO> listCat = new ArrayList<QLKHDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "select HOADON.ID,HOADON.NGAYMUA,HOADON.TRANGTHAI,CHITIETHOADON.TONGTIEN,CHITIETHOADON.SOLUONG,KHACHHANG.TENKHACHHANG,SANPHAM.TENSANPHAM,HOADON.ANHSANPHAM\n" +
                        "from CHITIETHOADON inner join SANPHAM on CHITIETHOADON.IDSANPHAM = SANPHAM.ID\n" +
                        "inner join HOADON\n" +
                        "on HOADON.ID = CHITIETHOADON.IDHOADON\n" +
                        "inner join KHACHHANG\n" +
                        "on HOADON.IDKHACHHANG = KHACHHANG.ID where HOADON.TRANGTHAI like N'Chưa Giao'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    QLKHDTO qlkhdto = new QLKHDTO();
                    qlkhdto.setId(resultSet.getInt("ID"));
                    qlkhdto.setTongtien(resultSet.getFloat("TONGTIEN"));
                    qlkhdto.setNgaymua(resultSet.getString("NGAYMUA"));
                    qlkhdto.setTrangthai(resultSet.getString("TRANGTHAI"));
                    qlkhdto.setSoluong(resultSet.getInt("SOLUONG"));
                    qlkhdto.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    qlkhdto.setTensanpham(resultSet.getString("TENSANPHAM"));
                    qlkhdto.setAnhsanpham(resultSet.getString("ANHSANPHAM"));


                    listCat.add(qlkhdto);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }

    public int getSLDHcg() {
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select COUNT(HOADON.ID) as 'soluongchuagiao' \n" +
                        "from CHITIETHOADON inner join SANPHAM on CHITIETHOADON.IDSANPHAM = SANPHAM.ID\n" +
                        "inner join HOADON\n" +
                        "on HOADON.ID = CHITIETHOADON.IDHOADON\n" +
                        "inner join KHACHHANG\n" +
                        "on HOADON.IDKHACHHANG = KHACHHANG.ID where HOADON.TRANGTHAI like N'Chưa Giao'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("soluongchuagiao");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return soluong;
    }

    public int getSLDHdg() {
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select COUNT(HOADON.ID) as 'soluongdagiao' \n" +
                        "from CHITIETHOADON inner join SANPHAM on CHITIETHOADON.IDSANPHAM = SANPHAM.ID\n" +
                        "inner join HOADON\n" +
                        "on HOADON.ID = CHITIETHOADON.IDHOADON\n" +
                        "inner join KHACHHANG\n" +
                        "on HOADON.IDKHACHHANG = KHACHHANG.ID where HOADON.TRANGTHAI like N'Đã giao'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("soluongdagiao");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return soluong;
    }

    public boolean updateTrangthai(int id, String trangthai) {

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE HOADON SET TRANGTHAI= N'" + trangthai + "'WHERE id = " + id;


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

            return true;
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu ");
            e.printStackTrace();
            return false;
        }
    }
}
