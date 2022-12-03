package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CTHDDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CTHDDAO {
    Connection objConn;
    public CTHDDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<CTHDDTO> getAll(){
        List<CTHDDTO> listCat = new ArrayList<CTHDDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM CHITIETHOADON ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    CTHDDTO cthddto = new CTHDDTO();
                    cthddto.setIdhoadon(resultSet.getInt("IDHOADON"));
                    cthddto.setIdsanpham(resultSet.getInt("IDSANPHAM"));
                    cthddto.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    cthddto.setSoluong(resultSet.getString("SOLUONG"));
                    cthddto.setTongtien(resultSet.getString("TONGTIEN"));

                    listCat.add(cthddto);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public void insertRow (CTHDDTO cthddto){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO CHITIETHOADON(TENKHACHHANG,SOLUONG,TONGTIEN) VALUES (N'" + cthddto.getTenkhachhang() + "',N'"+cthddto.getSoluong()+"','"+cthddto.getTongtien()+"')";

                String generatedColumns[] = { "ID" };

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
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
    }

    public void updateRow(CTHDDTO cthddto){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE CHITIETHOADON SET name= N'" + cthddto.getTenkhachhang()+"',N'"+ cthddto.getSoluong()+"','"+ cthddto.getTongtien()+ "'WHERE id = " + cthddto.getIdhoadon();
                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);

                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }

    public int getDtT12(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut12' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-12-1' AND '"+nam+"-12-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut12");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT11(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut11' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-11-1' AND '"+nam+"-11-30'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut11");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT10(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut10' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-10-1' AND '"+nam+"-10-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut10");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT9(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut9' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-09-1' AND '"+nam+"-09-30'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut9");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT8(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut8' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-08-1' AND '"+nam+"-08-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut8");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT7(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut7' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-07-1' AND '"+nam+"-07-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut7");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT6(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut6' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-06-1' AND '"+nam+"-06-30'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut6");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT5(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut5' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-05-1' AND '"+nam+"-5-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut5");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT4(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut4' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-04-1' AND '"+nam+"-04-30'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut4");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT3(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut3' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-03-01' AND '"+nam+"-03-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut3");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }


    public int getDtT2(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut2' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-02-1' AND '"+nam+"-02-28'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut2");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
    public int getDtT1(String nam){
        int soluong = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "select  SUM(b.TONGTIEN*b.SOLUONG) as 'doanhthut1' FROM HOADON a inner join CHITIETHOADON b on a.ID  = b.IDSANPHAM where a.NGAYMUA  BETWEEN  '"+nam+"-01-01' AND '"+nam+"-01-31'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    soluong = resultSet.getInt("doanhthut1");

                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  soluong;
    }
}
