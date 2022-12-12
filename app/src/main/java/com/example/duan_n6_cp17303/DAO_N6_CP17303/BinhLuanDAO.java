package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.BinhLuanDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BinhLuanDAO {
    Connection objConn;
    public BinhLuanDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<BinhLuanDTO> getAll(){
        List<BinhLuanDTO> listCat = new ArrayList<BinhLuanDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM BINHLUAN ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
                    binhLuanDTO.setIdbinhluan(resultSet.getInt("ID"));
                    binhLuanDTO.setBinhluan(resultSet.getString("BINHLUAN"));
                    binhLuanDTO.setIdsanpham(resultSet.getInt("IDSANPHAM"));
                    binhLuanDTO.setIdkhachhang(resultSet.getInt("IDKHACHHANG"));

                    listCat.add(binhLuanDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public void insertRow (BinhLuanDTO binhLuanDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO BINHLUAN(BINHLUAN) VALUES (N'" + binhLuanDTO.getBinhluan() +"')";

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

    public void updateRow(BinhLuanDTO binhLuanDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE BINHLUAN SET name= N'" + binhLuanDTO.getBinhluan()+ "'WHERE id = " + binhLuanDTO.getIdbinhluan();


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
    public List<BinhLuanDTO> getAll1(int idsp) {
        List<BinhLuanDTO> listCat = new ArrayList<BinhLuanDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "select BINHLUAN.BINHLUAN,KHACHHANG.TENKHACHHANG\n" +
                        "from BINHLUAN \n" +
                        "inner join SANPHAM on BINHLUAN.IDSANPHAM = SANPHAM.ID \n" +
                        "inner join KHACHHANG on BINHLUAN.IDKHACHHANG = KHACHHANG.ID where SANPHAM.ID like '"+idsp+"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    BinhLuanDTO binhLuanDTO = new BinhLuanDTO();

                    binhLuanDTO.setBinhluan(resultSet.getString("BINHLUAN"));
                    binhLuanDTO.setTenkhachhang(resultSet.getString("TENKHACHHANG"));

                    listCat.add(binhLuanDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        return listCat;
    }
    public void deleteRow(int id ){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "DELETE FROM BINHLUAN WHERE id = " + id;

                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Delete");

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi xóa dữ liệu " );
            e.printStackTrace();
        }
    }
}
