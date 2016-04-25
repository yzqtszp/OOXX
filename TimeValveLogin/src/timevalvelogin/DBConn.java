package timevalvelogin;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConn {
	
	private static String dbServer = "";
	  private static String dbDatabase = "";
	  private static String dbLogin = "";
	  private static String dbPassword = "";
	  private static Connection conn;

	  public static Connection setDBConn()
	  {
	    DBConn localDBConn = new DBConn();
	    localDBConn.loadDrivers();
	    try
	    {
	      Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	      String str = "jdbc:jtds:sqlserver://" + dbServer + ":1433/" + dbDatabase + "";
	      conn = DriverManager.getConnection(str, dbLogin, dbPassword);
	      System.out.println("===============================================================");
	    }
	    catch (Exception localException)
	    {
	      conn = null;
	      localException.printStackTrace();
	    }
	    return conn;
	  }

	  public static void closeConnection()
	  {
	    try
	    {
	      conn.close();
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	  }

	  public void loadDrivers()
	  {
	    InputStream localInputStream = getClass().getResourceAsStream("db.properties");
	    Properties localProperties = new Properties();
	    try
	    {
	      localProperties.load(localInputStream);
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	      System.out.println("不能读取属性文件");
	      return;
	    }
	    dbServer = localProperties.getProperty("Server").trim();
	    dbDatabase = localProperties.getProperty("Database").trim();
	    dbLogin = localProperties.getProperty("Login").trim();
	    dbPassword = localProperties.getProperty("Password").trim();
	  }

}
