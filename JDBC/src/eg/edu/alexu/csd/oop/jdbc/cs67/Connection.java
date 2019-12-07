package eg.edu.alexu.csd.oop.jdbc.cs67;

import eg.edu.alexu.csd.oop.db.databasee;

import java.io.File;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Connection implements java.sql.Connection{
	boolean isClosed = false;
	private String path ;
	private databasee db ;

	public databasee getDb() {
		return db;
	}

	public void setDb(databasee db) {
		this.db = db;
	}

	public Connection(String s){
		this.path = s ;
		this.db = new databasee();
		this.db.getH().setPath(new File(path));
	}
	
	@Override
	public void close() throws SQLException {
		if (isClosed) {
			throw new SQLException("Connection is closed.");
		}
		isClosed = true ;
	}
	
	@Override
	public Statement createStatement() throws SQLException {
        if (isClosed) {
            throw new SQLException("Connection is closed.");
        }
        else {
            Statement stat = new Statement();
            stat.SetConnection(this);
            return stat;
        }
    }

	
	//////////////////////////////////////////////////////

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void abort(Executor executor) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearWarnings() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void commit() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob createBlob() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob createClob() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob createNClob() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAutoCommit() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCatalog() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getClientInfo() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClientInfo(String name) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHoldability() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DatabaseMetaData getMetaData() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNetworkTimeout() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSchema() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTransactionIsolation() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLWarning getWarnings() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClosed() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReadOnly() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(int timeout) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String nativeSQL(String sql) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback(Savepoint savepoint) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCatalog(String catalog) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(Properties properties) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(String name, String value) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHoldability(int holdability) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReadOnly(boolean readOnly) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Savepoint setSavepoint() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Savepoint setSavepoint(String name) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchema(String schema) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTransactionIsolation(int level) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

}
