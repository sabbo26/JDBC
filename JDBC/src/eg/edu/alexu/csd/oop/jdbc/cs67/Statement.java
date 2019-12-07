package eg.edu.alexu.csd.oop.jdbc.cs67;

import eg.edu.alexu.csd.oop.db.*;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Vector;

public class Statement implements java.sql.Statement {

	private parse parser = new parse();
	private Vector<String> list = new Vector<>();
	private int QueryTimeout;
	private boolean isClosed = false;
	private Connection MyConnection;

	@Override
	public void addBatch(String sql) throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		sql = sql.toLowerCase();
		int x = parser.Check(sql);
		if (x == 7 || x == 8 || x == 9){
			list.add(sql);
		}
		else if (x == 0){
			throw new SQLException("invalid sql query");
		}
		else{
			throw new SQLException("invalid sql to be added to batch");
		}
	}   // add new sql query

	@Override
	public void clearBatch() throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		list.removeAllElements();

	}  // clear all queries

	@Override
	public boolean execute(String sql) throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		sql = sql.toLowerCase();
		int x = parser.Check(sql);
		if(x == 0 ){
			throw new SQLException("invalid sql query");
		}
		else if (x == 1 || x == 2 || x == 3 || x == 4){
			return MyConnection.getDb().executeStructureQuery(sql);
		}
		else if (x == 8 || x == 7 || x == 9 || x == 10 || x == 11){
			if(x == 7 ){
				int total = MyConnection.getDb().executeUpdateQuery(sql);
				if(total == 1){
					return true ;
				}
				else
					return false ;
			}
			if(x == 8 || x == 9 ){
				int total = MyConnection.getDb().executeUpdateQuery(sql);
				if(total != 0){
					return true ;
				}
				else
					return false ;
			}
			if( x == 10 || x == 11){
				int total = MyConnection.getDb().executeUpdateQuery(sql);
				if(total != 0){
					return true ;
				}
				else
					return false ;
			}
		}
		else if (x == 5 || x == 6 ){
			Object[][] temp = MyConnection.getDb().executeQuery(sql);
			if(temp.length == 1 && temp[0].length==1 && temp[0][0] == null)
				return false ;
			return true ;
		}
		return false ;
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		sql = sql.toLowerCase();
		int x = parser.Check(sql);
		if(x == 5 || x == 6){
			Object[][] temp  = MyConnection.getDb().executeQuery(sql);
			table tem = new table();
			for(int i = 0 ; i < MyConnection.getDb().getColumnsToPrint().length ; i++){
				column c = new column();
				c.setname(MyConnection.getDb().getColumnsToPrint()[i]);
				if(temp[0][i].getClass() == Integer.class){
					c.settype("int");
				}
				else{
					c.settype("varchar");
				}
				for(int j = 0 ; j < temp.length ; j++){
					cell h = new cell();
					h.setcell(temp[j][i]);
					c.addcell(h);
				}
				tem.addcolumn(c);
			}
			ResultSet yalla = new ResultSet(tem);
			yalla.setStatement(this);
			return yalla ;
		}
		else{
			throw new SQLException("not a select statement or invalid sql");
		}
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		sql = sql.toLowerCase();
		int x = parser.Check(sql);
		if(x == 7 || x == 8 ||  x == 9 || x == 10 || x == 11 ){
			return MyConnection.getDb().executeUpdateQuery(sql);
		}
		else{
			throw new SQLException("not a valid sql query");
		}
	}

	@Override
	public void close() throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		isClosed = true;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		return QueryTimeout;
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		QueryTimeout = seconds;
	}

	@Override
	public int[] executeBatch() throws SQLException {
		Vector<Integer> c = new Vector<>();
		for (int i = 0; i < list.size(); i++) {
			String s = list.elementAt(i).toLowerCase();
			 c.add(this.MyConnection.getDb().executeUpdateQuery(s));
		}
		int[] counts = new int[c.size()];
		for (int j = 0; j < c.size(); j++) {
			counts[j] = c.elementAt(j);
		}
		return counts;
	}

	@Override
	public Connection getConnection() throws SQLException {
        if (isClosed){
            throw new SQLException("Statement is closed.");
            }
        else
            return MyConnection;
    }
 
    public void SetConnection(Connection c)
    {
        MyConnection = c ;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public <T> T unwrap(Class<T> iface) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxFieldSize() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxFieldSize(int max) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxRows() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxRows(int max) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEscapeProcessing(boolean enable) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public SQLWarning getWarnings() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearWarnings() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCursorName(String name) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet getResultSet() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUpdateCount() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMoreResults() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFetchDirection(int direction) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFetchDirection() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFetchSize(int rows) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFetchSize() throws java.lang.UnsupportedOperationException{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetConcurrency() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetType() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMoreResults(int current) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getGeneratedKeys() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getResultSetHoldability() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isClosed() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPoolable(boolean poolable) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPoolable() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeOnCompletion() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCloseOnCompletion() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

}
