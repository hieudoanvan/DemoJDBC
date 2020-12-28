import java.sql.*;

public class StatementDemo {
   static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
   static final String DB_URL = "jdbc:mariadb://localhost:3311/students";

   //  Ten nguoi dung va mat khau cua co so du lieu
   static final String USER = "root";
   static final String PASS = "hieuthuy12";

   public static void main(String[] args) {
      Connection conn = null;
    Statement stmt = null;
      try{
         // Buoc 2: Dang ky Driver
         Class.forName(JDBC_DRIVER);

         // Buoc 3: Mo mot ket noi
         System.out.println("Dang ket noi toi co so du lieu ...");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);

         // Buoc 4: Thuc thi truy van
         System.out.println("Tao cac lenh truy van SQL ...");
         stmt = conn.createStatement();
         String sql = " UPDATE customers SET address='Tay Nguyen' WHERE id=1 ";

         // Chung ta kiem tra xem no co tra ve mot true Result Set hay khong
         Boolean ret = stmt.execute(sql);
         System.out.println("\nGia tri tra ve la : " + ret.toString() );

         // Chung ta cap nhat diem thi cua ban ghi co mssv = 3;
         int rows = stmt.executeUpdate(sql);
         System.out.println("So hang bi tac dong : " + rows );

         // Lua chon tat ca ban ghi va hien thi.
         sql = "SELECT id, name, address FROM customers";
         ResultSet rs = stmt.executeQuery(sql);

         // Buoc 5: Lay du lieu tu Result Set
         while(rs.next()){
            // Lay du lieu boi su dung ten cot
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");

            // Hien thi cac gia tri
            System.out.print("\nid: " + id);
            System.out.print("\nName: " + name);
            System.out.println("\nDiaChi: " + address);
            System.out.print("\n=================");
         }
         // Buoc 6: Don sach moi truong va giai phong resource
         rs.close();
         stmt.close();
         conn.close();
      }catch(SQLException se){
         // Xu ly cac loi cho JDBC
         se.printStackTrace();
      }catch(Exception e){
         // Xu ly cac loi cho Class.forName
         e.printStackTrace();
      }finally{
         // Khoi finally duoc su dung de dong cac resource
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }
         try{
            if(conn!=null)
               conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }// Ket thuc khoi finally
      }// Ket thuc khoi try
   }
}
