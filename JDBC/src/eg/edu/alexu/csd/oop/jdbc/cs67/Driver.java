package eg.edu.alexu.csd.oop.jdbc.cs67;

import java.io.File;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Driver implements java.sql.Driver {

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if (!acceptsURL(url)) {
			return null;
		}
		File f = (File) info.get("path");
		String path = f.getAbsolutePath();
		Connection co = new Connection(path);
		return co;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		if (url.contains("jdbc:xmldb://localhost")) {
			return true;
		}
		return false;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		File f = (File) info.get("path");
		String path = f.getAbsolutePath();
		DriverPropertyInfo D[] = new DriverPropertyInfo[1];
		D[0] = new DriverPropertyInfo("path",path);
		return D ;
	}

	@Override
	public int getMajorVersion() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean jdbcCompliant() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Logger getParentLogger() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
