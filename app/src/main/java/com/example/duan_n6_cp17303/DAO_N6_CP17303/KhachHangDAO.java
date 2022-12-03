package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.KhachHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    Connection objConn;
    public KhachHangDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<KhachHangDTO> getAll(){
        List<KhachHangDTO> listCat = new ArrayList<KhachHangDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM KHACHHANG ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    KhachHangDTO khachHangDTO = new KhachHangDTO();
                    khachHangDTO.setIdkhachhang(resultSet.getInt("ID"));
                    khachHangDTO.setTenkhachhang(resultSet.getString("TENKHACHHANG"));
                    khachHangDTO.setPhone(resultSet.getString("PHONE"));
                    khachHangDTO.setEmail(resultSet.getString("EMAIL"));
                    khachHangDTO.setDiachi(resultSet.getString("DIACHI"));
                    khachHangDTO.setUsername(resultSet.getString("USERNAME"));


                    listCat.add(khachHangDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public void insertRow (KhachHangDTO khachHangDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO KHACHHANG(TENKHACHHANG,PHONE,EMAIL,DIACHI) VALUES (N'" + khachHangDTO.getTenkhachhang() + "','"+khachHangDTO.getPhone()+ "','"+khachHangDTO.getEmail()+"',N'"+khachHangDTO.getDiachi()+"')";

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

    public void updateRow(KhachHangDTO khachHangDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE KHACHHANG SET name= N'" + khachHangDTO.getTenkhachhang()  +"',N'"+ khachHangDTO.getPhone() +"','"+ khachHangDTO.getEmail() +"',N'"+ khachHangDTO.getDiachi() + "'WHERE id = " + khachHangDTO.getIdkhachhang();


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
}
