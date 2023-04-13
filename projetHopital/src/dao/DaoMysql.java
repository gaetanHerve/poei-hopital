package dao;

public abstract class DaoMysql {
	private static final MysqlCredentials creds = new MysqlCredentials();
	
	static protected String db = creds.getDbName();
	static protected String login = creds.getLogin();
	static protected String pwd = creds.getPwd();
	static protected String connectionStr = "jdbc:mysql://localhost:3306/" + db;

}
