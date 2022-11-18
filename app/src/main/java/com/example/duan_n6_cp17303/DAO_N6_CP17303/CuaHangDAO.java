package com.example.duan_n6_cp17303.DAO_N6_CP17303;

import android.util.Log;

import com.example.duan_n6_cp17303.DBHelper_N6_CP17303.MyDBHelper;
import com.example.duan_n6_cp17303.DTO_N6_CP17303.CuaHangDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CuaHangDAO {
    Connection objConn;
    public CuaHangDAO(){
        // hàm khởi tạo để mở kết nối
        MyDBHelper db = new MyDBHelper();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public List<CuaHangDTO> getAll(){
        List<CuaHangDTO> listCat = new ArrayList<CuaHangDTO>();

        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM CUAHANG ";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    CuaHangDTO objCat = new CuaHangDTO();
                    objCat.setTencuahang(resultSet.getString("TENCUAHANG"));
                    objCat.setUsername(resultSet.getString("USERNAME"));
                    objCat.setPass(resultSet.getString("PASS"));
                    objCat.setDiachi(resultSet.getString("DIACHI"));
                    objCat.setPhone(resultSet.getString("PHONE"));
                    objCat.setIdsanpham(resultSet.getInt("IDSANPHAM"));
                    objCat.setIdlienhe(resultSet.getInt("IDLIENHE"));
                    objCat.setIdkhachhang(resultSet.getInt("IDKHACHHANG"));


                    listCat.add(objCat);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  listCat;
    }
}
