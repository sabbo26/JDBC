package eg.edu.alexu.csd.oop.jdbc.cs67;


import eg.edu.alexu.csd.oop.db.table;

import java.sql.SQLException;

public class ResultSetMetaData implements java.sql.ResultSetMetaData {
	private table t ;

	public ResultSetMetaData(table t) {
		this.t = t;
	}

	@Override
	public int getColumnCount() throws SQLException {
		return t.gettable().size();
	}

	@Override
	public String getColumnLabel(int arg0) throws SQLException { 
		int col = arg0 - 1;
		if (col < 0 || col > t.gettable().size() - 1) {
			throw new SQLException("invalid");
		}
		return t.gettable().elementAt(col).getname();
	}

	@Override
	public String getColumnName(int arg0) throws SQLException {
		int col = arg0 - 1;
		if (col < 0 || col > t.gettable().size() - 1) {
			throw new SQLException("invalid");
		}
		return t.gettable().elementAt(col).getname();
	}

	@Override
	public int getColumnType(int arg0) throws SQLException {
		int col = arg0 - 1;
		if (col < 0 || col > t.gettable().size() - 1) {
			throw new SQLException("invalid");
		}
		if (t.gettable().elementAt(col).gettype().equals("int")) {
			return java.sql.Types.INTEGER;
		} else {
			return java.sql.Types.VARCHAR;
		}
	}

	@Override
	public String getTableName(int arg0) throws SQLException {
		int col = arg0 - 1;
		if (col < 0 || col > t.gettable().size() - 1) {
			throw new SQLException("invalid");
		}
		return t.getname();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

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
	public String getCatalogName(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnClassName(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColumnDisplaySize(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnTypeName(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPrecision(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScale(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSchemaName(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAutoIncrement(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCaseSensitive(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCurrency(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDefinitelyWritable(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isNullable(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isReadOnly(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSigned(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWritable(int arg0) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

}
