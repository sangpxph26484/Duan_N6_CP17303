package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.database.Cursor;
import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.SanPhamDTO;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.TaiKhoanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    Connection objConn;
    public TaiKhoanDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }

    public List<TaiKhoanDTO> getAll(){
        List<TaiKhoanDTO> listCat = new ArrayList<TaiKhoanDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM TAIKHOAN ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    TaiKhoanDTO objCat = new TaiKhoanDTO();
                    objCat.setIdtaikhoan(resultSet.getInt("ID"));
                    objCat.setUsername(resultSet.getString("USENAME"));
                    objCat.setPassword(resultSet.getString("PASS"));
                    objCat.setAvatar(resultSet.getString("AVATAR"));
                    objCat.setIdkhachhang(resultSet.getInt("IDKHACHHANG"));
                    objCat.setIdcuahang(resultSet.getInt("IDCUAHANG"));



                    listCat.add(objCat);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public void insertRow (TaiKhoanDTO taiKhoanDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO TAIKHOAN(USENAME,PASS) VALUES ('" + taiKhoanDTO.getUsername()+"',N'"+taiKhoanDTO.getPassword()  +"')";

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

    public void updateRow(TaiKhoanDTO taiKhoanDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE TAIKHOAN SET name= '" + taiKhoanDTO.getUsername()+"','"+ taiKhoanDTO.getPassword() + "'WHERE id = " + taiKhoanDTO.getIdtaikhoan();


                PreparedStatement stmt = this.objConn.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
//    public boolean checkuser(String username){
//        Cursor cursor = db.rawQuery("Select * from tb_user where username = ?",new String[]{username});
//        if(cursor.getCount()>0){
//            return true;
//        }else
//            return  false;
//    }
//
//    public boolean checkuserpass(String username,String password){
//        Cursor cursor = db.rawQuery("Select * from tb_user where username = ? and password = ?",new String[]{username,password});
//        if (cursor.getCount()>0){
//            return true;
//        }else
//            return  false;
//    }
}
