package events.eventutils;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





import exceptions.HostelNotFoundException;
import exceptions.NotEnoughItemsException;

public class Inventory {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "org.h2.Driver";  
	   static final String DB_URL = "jdbc:h2:~/test";
	   static Statement stmt;
	   static Connection conn;

	   //  Database credentials
	   static final String USER = "sa";
	   static final String PASS = "";
	   
	   static HashMap<String, Integer> items;
	   
	   public Inventory()
	   {
		   
	   }
	   
	   
	   
	   public static void set(String item, int quantity)
	   {
		   openConnection();
		   try
		   {
			   stmt.executeUpdate("INSERT INTO Inventory VALUES(\'" + item + "\', " + quantity + ");");
		   }
		   catch(SQLException se)
		   {
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				   closeConnection();
				   }
	   }
	   public static HashMap<String, Integer> getItems()
	   {
		   openConnection();
		   items = new HashMap<String, Integer>();

		   try
		   {
			   ResultSet rs = stmt.executeQuery("SELECT * FROM Inventory;");
			   while (rs.next())
			   {
				   items.put(rs.getString("Item"), rs.getInt("Quantity"));
			   }
		   }
		   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				   closeConnection();
				   }
		   
		   return items;
	   }
	   
	   public static void setQuantity(String item, int quantity)
	   {
		   items = getItems();
		   openConnection();

		   try
		   {
			   stmt.executeUpdate("UPDATE Inventory SET Quantity = " + (quantity)  + "WHERE Item = \'" + item + "\';");
		   }
		   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				   closeConnection();
				   }
	   }
	   
	   
	   public static int getQuantity(String item) throws NotEnoughItemsException
	   {
		   int ret =0;
		   openConnection();

		   try
		   {
			   ResultSet rs = stmt.executeQuery("SELECT Quantity FROM Inventory WHERE Item = \'" + item + "\';");
			   if (!rs.isBeforeFirst())
			   {
					throw new NotEnoughItemsException();

				}
				
				rs.beforeFirst();
				rs.next();
				ret = rs.getInt("Quantity");
		   }
		   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				   closeConnection();
				   }
		   return ret;
	   }
	  
	   
	   public static void  deposit(String item, int quantity)
	   {
		   items = getItems();
		   openConnection();

		   try
		   {
			   stmt.executeUpdate("UPDATE Inventory SET Quantity = " + (quantity + getQuantity(item))  + "WHERE Item = \'" + item + "\';");
		   }
		   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				   closeConnection();
				   }
	   }
	   
	  
	   
	   public static void withdraw(String item, int quantity) throws NotEnoughItemsException
	   {
		   items = getItems();


		   try
		   {
			   int num = getQuantity(item);
			   //System.out.println("TRAAAAAAAAAAAAAAAAAAAACE: For item: " + item + " Quantity existing: " + num + "Rewquested :" + quantity);
			   if (quantity > num)
			   {
				   throw new NotEnoughItemsException();
			   }
			   openConnection();
			   stmt.executeUpdate("UPDATE Inventory SET Quantity = " + (num - quantity)  + "WHERE Item = \'" + item + "\';");
		   }
		   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   
			   }finally{
				   closeConnection();
				   }
	   }
	   
	   public static void displayItems(){
			items = getItems();
			   openConnection();

			if(items != null){
				for(Map.Entry<String, Integer> entry : items.entrySet()){
					System.out.println("Items : " + entry.getKey() + ", Quantity : " + entry.getValue());
				}
			}else{
				System.out.println("No items exist");
			}
			
		}
	   
		public static void truncate()
		{
			openConnection();
			try
			{
				stmt.execute("TRUNCATE TABLE ScoreBoard;");
			}
			catch(Exception e){}
			closeConnection();
		}
		
	   
	   private static void openConnection()
	   {
		   try{
				Class.forName("org.h2.Driver");
				
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
			}
			catch(Exception e){
				e.printStackTrace();
			}
	   }
	   
	   
		private static void closeConnection()
		{
			 //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		}

}
