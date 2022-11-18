package com.example.duan_n6_cp17303.DBHelper_N6_CP17303;
    import android.os.StrictMode;
    import android.util.Log;
    import java.sql.Connection;
    import java.sql.DriverManager;
public class MyDBHelper {

    Connection connection;
    final String TAG = "zzzzzz";

    public Connection openConnect(){
        String ip = "103.179.188.76", port = "1433", user = "CP17303_n06", pass = "nhom06@123", db = "CP17303_n06";
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db +";user=" + user +";password=" + pass +";";
            this.connection = DriverManager.getConnection(connectUrl);
            Log.d(TAG, "openConnect: OK");
        } catch (Exception e) {
            Log.e(TAG, "getCollection: Loi ket noi CSDL" );
            e.printStackTrace();
        }
        return connection;
    }
}
