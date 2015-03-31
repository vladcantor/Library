package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {

	  private static JdbcUtils instance=null;
	    private Connection connection=null;
	    private JdbcUtils(){

	    }

	    public static JdbcUtils getInstance(){
	        if (instance==null)
	            instance=new JdbcUtils();
	        return instance;
	    }

	    private Connection getNewConnection(){
	        String driver=System.getProperty("mdb.driver");
	        String url=System.getProperty("mdb.url");
	        String user=System.getProperty("mdb.user");
	        String pass=System.getProperty("mdb.pass");
	        Connection con=null;
	        try {
	            Class.forName(driver);
	            System.out.println("Driver "+driver+" loaded");
	            if ((user==null) || (pass==null))
	                con= DriverManager.getConnection(url);
	            else
	                con= DriverManager.getConnection(url,user,pass);
	        } catch (ClassNotFoundException e) {
	            System.out.println("Error loading driver "+e);
	        } catch (SQLException e) {
	            System.out.println("Error getting connection "+e);
	        }
	        return con;
	    }

	    public Connection getConnection(){
	        try {
	            if (connection==null || connection.isClosed())
	                connection=getNewConnection();

	        } catch (SQLException e) {
	            System.out.println("Error DB "+e);
	        }
	        return connection;
	    }

	}
