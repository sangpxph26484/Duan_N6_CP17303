package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
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

                    TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
                    taiKhoanDTO.setIdtaikhoan(resultSet.getInt("ID"));
                    taiKhoanDTO.setUsername(resultSet.getString("USERNAME"));
                    taiKhoanDTO.setPassword(resultSet.getString("PASS"));
                    taiKhoanDTO.setAvatar(resultSet.getString("AVATAR"));




                    listCat.add(taiKhoanDTO);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
    public boolean insertRow (TaiKhoanDTO taiKhoanDTO){

        try {
            if (this.objConn != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO TAIKHOAN(USERNAME,PASS) VALUES ('" + taiKhoanDTO.getUsername()+"',N'"+taiKhoanDTO.getPassword()  +"')";

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
            return true;

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
            return false;
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

    public int checkLogin(String user, String pass) {

        List<TaiKhoanDTO> listCat = new ArrayList<TaiKhoanDTO>();

        try {
            if (this.objConn != null) {

                String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = '" + user + "' AND PASS = '" + pass + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sql); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();

                    taiKhoanDTO.setUsername(resultSet.getString("USERNAME"));// truyền tên cột dữ liệu
                    taiKhoanDTO.setPassword(resultSet.getString("PASS")); // tên cột dữ liệu là pass

                    listCat.add(taiKhoanDTO);

                }
            }

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        if (listCat.size() == 0) {
            return -1;
        }

        return 1;
    }

    public int checkUser(String name) {
        List<TaiKhoanDTO> listCat = new ArrayList<TaiKhoanDTO>();

        try {
            if (this.objConn != null) {

                String sql = "SELECT * FROM TAIKHOAN WHERE USERNAME = '" + name +"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sql); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();

                    taiKhoanDTO.setUsername(resultSet.getString("USERNAME"));// truyền tên cột dữ liệu


                    listCat.add(taiKhoanDTO);

                }
            }

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        if (listCat.size() == 0) {
            return -1;
        }

        return 1;
    }
    public int checkPass(String pass) {
        List<TaiKhoanDTO> listCat = new ArrayList<TaiKhoanDTO>();

        try {
            if (this.objConn != null) {

                String sql = "SELECT * FROM TAIKHOAN WHERE PASS = '" + pass + "'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sql); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();

                    taiKhoanDTO.setPassword(resultSet.getString("PASS"));// truyền tên cột dữ liệu


                    listCat.add(taiKhoanDTO);

                }
            }

        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }

        if (listCat.size() == 0) {
            return -1;
        }

        return 1;
    }
    public boolean updateMatKhau(String matkhau, String user) {
        try {
            if (this.objConn != null) {
                String sqlUpdate = "UPDATE TAIKHOAN SET PASS = '" + matkhau + "' where username = '" + user + "'";

                PreparedStatement statement = this.objConn.prepareStatement(sqlUpdate);
                statement.execute();
                Log.e("zzzz", "updateMatKhau: thanhcong");
            }
            return true;
        } catch (Exception e) {
            Log.e("zzzz", "updateSP : co loi sua du lieu");
            e.printStackTrace();
            return false;
        }
    }
}
