package events.eventutils;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import exceptions.HostelNotFoundException;
import participatingbody.Hostel;

public class ScoreBoard {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "org.h2.Driver";  
	   static final String DB_URL = "jdbc:h2:~/test";
	   static Statement stmt;
	   static Connection conn;

	   //  Database credentials
	   static final String USER = "sa";
	   static final String PASS = "";
	   
	   
	   /**
	    *It is assumed that the table SCOREMAP is already created 
	    * */
	   
	/**
	 * Constructor
	 */
	public ScoreBoard() {
		


	}
	
	/**
	 * HashMap to contain scores of hostels
	 */
	private static HashMap<String, Long> scoresMap;
	
	/**
	 * Getter for scores map
	 * @return
	 */
	
	
	public static HashMap<String, Long> getScoresMap(){
		//HashMap<String, Long> ret = new HashMap<String, Long>();
		   openConnection();

		try{
			      scoresMap = new HashMap<String, Long>();
			
			      ResultSet rs = stmt.executeQuery("SELECT * FROM SCOREMAP");

			      //STEP 5: Extract data from result set
			      while(rs.next()){
			         //Retrieve by column name
			         String hos  = rs.getString("Hostel");
			         long scr = rs.getLong("Score");

			         //set hash map
			         //System.out.println(hos);
			         scoresMap.put(hos, scr);
			         
			         
			         
			      }
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				   closeConnection();
				   
			     
			   }//end try
		return scoresMap;
		   
	}

	/**
	 * Setter for scores map
	 * @param scoresMap
	 */
	public static void setScoresMap(Hostel hostel, long score) {
		   openConnection();

		   try{

			      
			      //STEP 4: Execute a query			     
			      stmt.executeUpdate("INSERT INTO SCOREMAP VALUES('" + hostel.getName() + "', " + score + ");");
			     	      
			      
			      
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }finally{
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
			   }//end try
	}

	/**
	 * Get scores of a particular hostel
	 * @param hostel
	 * @return score
	 * @throws HostelNotFoundException
	 */
	public static Long getScores(Hostel hostel) throws HostelNotFoundException{
		/*if(scoresMap.containsKey(hostel)){
			return scoresMap.get(hostel);
		}else{
			//Throw non existent hostel exception
			throw new HostelNotFoundException();
			
		}*/
		Long scr = null;
		   openConnection();

		try{
			ResultSet rs = stmt.executeQuery("SELECT Score FROM ScoreMap WHERE Hostel = \'" + hostel.getName() + "\';");
			
			if (!rs.isBeforeFirst())
					{
				
					throw new HostelNotFoundException();
				}
			
			
			rs.beforeFirst();
			rs.next();
			scr = rs.getLong("SCORE");
			
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }finally{
			   closeConnection();
			   
		     
		   }
		return scr;
	}
	
	/**
	 * Update scores of a hostel
	 * @param hostel
	 * @param score
	 * @param updateScoreType
	 * @throws HostelNotFoundException 
	 */
	public static void updateScores(Hostel hostel, Long difference, UpdateScoreType updateScoreType) throws HostelNotFoundException{
		//Update scores for a particular hostel
		/*if(scoresMap.containsKey(hostel)){
			
			if(updateScoreType == UpdateScoreType.ADD){
				score = scoresMap.get(hostel) + score;
			}else if(updateScoreType == UpdateScoreType.SUBTRACT){
				score = scoresMap.get(hostel) - score;
			}
			
			scoresMap.put(hostel,score);
		}else{
			//Throw non existent hostel exception
			throw new HostelNotFoundException();
		}*/
		int mul = 0;
		if (updateScoreType == UpdateScoreType.ADD)
		{
			mul = 1;
		}
		else
			mul = -1;
		   openConnection();

		
		try{
			stmt.executeUpdate("UPDATE ScoreMap SET Score = " + (getScores(hostel) + mul*difference) + " WHERE Hostel = \'" + hostel.getName() + "\';");
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
	
	/**
	 * Display scores of all Hostels
	 */
	public static void displayScores(){
		scoresMap = getScoresMap();


		if(scoresMap != null){
			for(Map.Entry<String, Long> entry : scoresMap.entrySet()){
				System.out.println("Hostel : " + entry.getKey() + ", Score : " + entry.getValue());
			}
		}else{
			System.out.println("No scores exist for any hostel");
		}
		
	}
	
	public static void displayPositions()
	{
		openConnection();
		
		try
		{
			ResultSet rs = stmt.executeQuery("SELECt * FROM ScoreMap ORDER BY Score DESC;");
			System.out.println("Positions:");
			
			int i = 0;

			while (rs.next())
			{
				i++;
				System.out.println("Hostel: " + rs.getString("Hostel") + " stands at position " + i);
			}
		}catch(Exception e){ System.out.println(e.getMessage());};
		closeConnection();
	}
	
	/**
	 * Display winner of Zephyr
	 */
	public static void displayWinner(){
		scoresMap = getScoresMap();
		if(scoresMap != null){
			
			HashMap<String, Long> winners = new HashMap<String,Long>();
			
			Long highestScore = Long.MIN_VALUE;
			
			for(Map.Entry<String, Long> entry : scoresMap.entrySet()){
				if(entry.getValue() > highestScore){
					winners.clear();
					winners.put(entry.getKey(), entry.getValue());
					highestScore = entry.getValue();
				}else if( entry.getValue().equals(highestScore)){
					winners.put(entry.getKey(), entry.getValue());
				}
			}
			
			if(winners.size() == 0){
				System.out.println("No winner of Zephyr!");
			}else if(winners.size() == 1){
				for(Map.Entry<String, Long> entry : winners.entrySet()){
					System.out.println("Hostel " + entry.getKey() + " is the winner of Zephyr with " + entry.getValue() + " points! Congratulations!" );
				}
			}else  if(winners.size() > 1){
				String winningHostels = "";
				Long winningScore = 0L;
				for(Map.Entry<String, Long> entry : winners.entrySet()){
					if(winningHostels.equals("")){
						winningHostels = entry.getKey();
					}else{
						winningHostels = winningHostels + ", " +entry.getKey();
					}
					
					winningScore = entry.getValue();
				}
				System.out.println("Hostels " + winningHostels + " are the joint winners of Zephyr with " + winningScore + " points! Congratulations!");
			}
			
		}else{
			System.out.println("No scores exist for any hostel");
		}
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
