import java.sql.*;

public class DemoJDBC {
   // Ten cua driver va dia chi URL cua co so du lieu
   static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
   static final String DB_URL = "jdbc:mariadb://localhost:3311/students";

   //  Ten nguoi dung va mat khau cua co so du lieu
   static final String USER = "root";
   static final String PASS = "hieuthuy12";

   public static void main(String[] args) throws ClassNotFoundException, SQLException {
      Connection conn = null;
      Statement stmt = null;
      // Buoc 2: Dang ky Driver
      Class.forName(JDBC_DRIVER);

      // Buoc 3: Mo mot ket noi
      System.out.println("Dang ket noi toi co so du lieu ...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // Buoc 4: Thuc thi truy van
      System.out.println("Tao cac lenh truy van SQL ...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT id,name,address FROM customers";
      ResultSet rs = stmt.executeQuery(sql);

      // Buoc 5: Lay du lieu tu Result Set
      while (rs.next()) {
         // Lay du lieu boi su dung ten cot
         int id = rs.getInt("id");
         String name = rs.getString("name");
         String address = rs.getString("address");

         // Hien thi cac gia tri
         System.out.print("\nId: " + id);
         System.out.print("\nName: " + name);
         System.out.println("\nDiaChi: " + address);
         System.out.print("\n=================");

      }
      // Buoc 6: Don sach moi truong va giai phong resource
      rs.close();
      stmt.close();
      conn.close();
   }
}// Ket thuc ViDuJDBC

