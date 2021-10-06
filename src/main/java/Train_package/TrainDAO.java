package Train_package;

import java.sql.*;

public  class TrainDAO {

     static Connection con=null;

    public static int getcount()
    {

        int count=0;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdatabase","root","Venkatesh@1999");

            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("select count from ticket where id=1");
            while(rs1.next())
                count = rs1.getInt(1);
           PreparedStatement ps = null;
          String str = "update ticket set count="+(count+1)+" where id=1";
           ps = con.prepareStatement(str);
            ps.executeUpdate();




        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }
    public Train findtrain(int trainNo){
        Train train= null;


        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdatabase","root","Venkatesh@1999");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from trains where train_no="+trainNo);
            while(rs.next()){
                train = new Train(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));


            }
        } catch (SQLException e) {
            System.out.println("train no. not found");

        }
        return train;

    }
}
