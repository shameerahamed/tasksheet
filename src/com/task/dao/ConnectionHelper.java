package com.task.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class ConnectionHelper extends HttpServlet{
	
	/*public Connection getConnection(HttpServletRequest request) {		
		 static javax.sql.DataSource dataSource;
		 java.sql.Connection myConnection;
		 try {
		  dataSource = getDataSource(request);
		  myConnection = dataSource.getConnection();
		  // do what you wish with myConnection
		 } catch (SQLException sqle) {
		    getServlet().log("Connection.process", sqle);
		 } finally {
		    //enclose this in a finally block to make
		    //sure the connection is closed
		    try {
		       myConnection.close();
		    } catch (SQLException e) {
		       getServlet().log("Connection.close", e);
		    }
		   }

		return null;
	}*/
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Connection getConnection() throws Exception{        
        String driver = "org.apache.derby.jdbc.ClientDriver";
        //String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        //String dbName="C:/Program Files/Java/jdk1.6.0_06/db/frameworks/NetworkServer/bin/TaskSheetDB";
        String dbName="TaskSheetDB";
        String connectionURL = "jdbc:derby://localhost:1527/" + dbName + ";create=false";
      //  String connectionURL = "jdbc:derby:" + dbName + ";create=false";
        Connection conn = null; 
	      //   Beginning of JDBC code sections   
	      //   ## LOAD DRIVER SECTION ##
	   try	        {
	          /*
	          **  Load the Derby driver. 
	          **     When the embedded Driver is used this action start the Derby engine.
	          **  Catch an error and suggest a CLASSPATH problem
	           */
	          Class.forName(driver); 
	          //System.out.println(driver + " loaded. ");
	      } catch(java.lang.ClassNotFoundException e)     {
	          System.err.print("ClassNotFoundException: ");
	          System.err.println(e.getMessage());
	          System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
	      }
	      //  Beginning of Primary DB access section
	      //   ## BOOT DATABASE SECTION ##
	     try {
	            // Create (if needed) and connect to the database
	            conn = DriverManager.getConnection(connectionURL);		 
	            //System.out.println("Connected to database " + dbName);	
	          
	        //   ## DATABASE SHUTDOWN SECTION ## 
		            /*** In embedded mode, an application should shut down Derby.
		               Shutdown throws the XJ015 exception to confirm success. ***/			
		            if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
		               boolean gotSQLExc = false;
		               try {
		                  DriverManager.getConnection("jdbc:derby:;shutdown=true");
		               } catch (SQLException se)  {	
		                  if ( se.getSQLState().equals("XJ015") ) {		
		                     gotSQLExc = true;
		                  }
		               }
		               if (!gotSQLExc) {
		               	  System.out.println("Database did not shut down normally");
		               }  else  {
		                  System.out.println("Database shut down normally");	
		               }  
		            }
		            return conn;
		            
		         //  Beginning of the primary catch block: uses errorPrint method
		         }  catch (Throwable e)  {   
		            /*       Catch all exceptions and pass them to 
		            **       the exception reporting method             */
		            System.out.println(" . . . exception thrown:");
		            errorPrint(e);
		         }	    
	     /*finally {
	    	 s.close();
	    	 conn.close();
	    	 System.out.println("Closed connection");
	    	  System.out.println("Working With Derby JDBC program ending.");
	     }*/
	    return conn;
    }
    
    
    //   ## DERBY EXCEPTION REPORTING CLASSES  ## 
    /***     Exception reporting methods
    **      with special handling of SQLExceptions
    ***/
      static void errorPrint(Throwable e) {
         if (e instanceof SQLException) 
            SQLExceptionPrint((SQLException)e);
         else {
            System.out.println("A non SQL error occured.");
            e.printStackTrace();
         }   
      }  // END errorPrint 

    //  Iterates through a stack of SQLExceptions 
      static void SQLExceptionPrint(SQLException sqle) {
         while (sqle != null) {
            System.out.println("\n---SQLException Caught---\n");
            System.out.println("SQLState:   " + (sqle).getSQLState());
            System.out.println("Severity: " + (sqle).getErrorCode());
            System.out.println("Message:  " + (sqle).getMessage()); 
            sqle.printStackTrace();  
            sqle = sqle.getNextException();
         }
   }  //  END SQLExceptionPrint   
      
      /***      Check for  Existence of table    ****/
      public static boolean wwdChk4Table (Connection conTst ) throws SQLException {
         boolean chk = true;
         boolean doCreate = false;
         try {
            Statement s = conTst.createStatement();
            ResultSet rs = s.executeQuery("select count(*) as count from tbl_picklist");
            if(rs.next()) {
            	int cnt1 = rs.getInt(1);
            	if (cnt1 == 0) {
            		s.execute("run 'tableentry.sql'");
            	}
            }
         }  catch (SQLException sqle) {
            String theError = (sqle).getSQLState();
            //   System.out.println("  Utils GOT:  " + theError);
            /** If table exists will get -  WARNING 02000: No row was found **/
            if (theError.equals("42X05"))   // Table does not exist
            {  return false;
             }  else if (theError.equals("42X14") || theError.equals("42821"))  {
                System.out.println("WwdChk4Table: Incorrect table definition. Drop table WISH_LIST and rerun this program");
                throw sqle;   
             } else { 
                System.out.println("WwdChk4Table: Unhandled SQLException" );
                throw sqle; 
             }
         }
         //  System.out.println("Just got the warning - table exists OK ");
         return true;
      }  /*** END wwdInitTable  **/
}
