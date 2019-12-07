package eg.edu.alexu.csd.oop.jdbc.cs67;

import eg.edu.alexu.csd.oop.db.table;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class ResultSet implements java.sql.ResultSet {
	private table t ;
	private int row_num = 0;
	private boolean isClosed = false;
	private Statement MyStatement ;

	public ResultSet(table t) {
		this.t = t;
	}

	@Override
	public boolean absolute(int row) throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (row == 0 ){
			row_num = 0 ;
			return false ;
		}
		else if (row > 0) {
			if (t.gettable().elementAt(0).getcolumn().size() < row){
				row_num = t.gettable().elementAt(0).getcolumn().size() + 1 ;
				return false;
			}
			row_num = row;
		}
		else{
			if (Math.abs(row) > t.gettable().elementAt(0).getcolumn().size()) {
				row_num = 0 ;
				return false;
			}
			row_num = t.gettable().elementAt(0).getcolumn().size() + 1 + row;
		}
		return true;
	}  // put the index on specific row

	@Override
	public void beforeFirst() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		row_num = 0;
	}  // put the index before first tow

	@Override
	public void close() throws SQLException {
		if (isClosed)
			throw new SQLException("Statement is closed.");
		isClosed = true;
	} // close the result set

	@Override
	public void afterLast() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		row_num = t.gettable().elementAt(0).getcolumn().size() + 1;

	} // put the index after last row

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (!t.ifColumnExists(columnLabel)) {
			throw new SQLException("invalid");
		}
		for (int i = 0; i < t.gettable().size(); i++) {
			if (t.gettable().elementAt(i).getname().equals(columnLabel)) {
				return i + 1;
			}
		}
		return 0;
	} // return column number

	@Override
	public boolean isBeforeFirst() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (row_num == 0)
			return true;
		return false;
	}

	@Override
	public boolean isAfterLast() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (row_num == t.gettable().elementAt(0).getcolumn().size() + 1)
			return true;
		return false;
	}

	@Override
	public boolean isFirst() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (row_num == 1)
			return true;
		return false;
	}

	@Override
	public boolean isLast() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (row_num == t.gettable().elementAt(0).getcolumn().size())
			return true;
		return false;
	}

	@Override
	public boolean first() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (t.gettable().elementAt(0).getcolumn().size() == 0)
			return false;
		row_num = 1;
		return true;
	}

	@Override
	public boolean last() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (t.gettable().elementAt(0).getcolumn().size() == 0)
			return false;
		row_num = t.gettable().elementAt(0).getcolumn().size();
		return false;
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		int col = columnIndex - 1;
		if (col > t.gettable().size() - 1 || col < 0 || !t.gettable().elementAt(col).gettype().equals("int")) {
			throw new SQLException("invalid");
		}
		return (int) t.gettable().elementAt(col).getcolumn().elementAt(row_num - 1).getcell();
	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (!t.ifColumnExists(columnLabel) || !t.getColumn(columnLabel).gettype().equals("int")) {
			throw new SQLException("invalid");
		}
		return (int) t.getColumn(columnLabel).getcolumn().elementAt(row_num - 1).getcell();
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException { /////// ????????????????????????
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		ResultSetMetaData rs = new ResultSetMetaData(t);
		return rs;
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Resultset is closed.");
		int col = columnIndex - 1;
		if (col > t.gettable().size() - 1 || col < 0) {
			throw new SQLException("invalid");
		}
		return t.gettable().elementAt(col).getcolumn().elementAt(row_num - 1).getcell();
	}

	@Override
	public Statement getStatement() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		else
			return MyStatement;
	}

	public void setStatement(Statement s) {
		MyStatement = s;
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		int col = columnIndex - 1;
		if (col > t.gettable().size() - 1 || col < 0) {
			throw new SQLException("invalid");
		}
		return t.gettable().elementAt(col).getcolumn().elementAt(row_num - 1).getcell().toString();
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if (!t.ifColumnExists(columnLabel)) {
			throw new SQLException("invalid");
		}
		return t.getColumn(columnLabel).getcolumn().elementAt(row_num - 1).getcell().toString();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return isClosed ;
	}

	@Override
	public boolean next() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if(row_num == t.gettable().elementAt(0).getcolumn().size()+1){
			return false ;
		}
		if (row_num == t.gettable().elementAt(0).getcolumn().size()) {
			row_num += 1;
			return false;
		}
		row_num += 1;
		return true ;
	}

	@Override
	public boolean previous() throws SQLException {
		if (isClosed)
			throw new SQLException("Resultset is closed.");
		if(row_num == 0){
			return false ;
		}
		if (row_num == 1) {
			row_num -= 1;
			return false;
		}
		row_num -= 1;
		return true ;
	}

	////////////////////////////////////////////////////////
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
	public boolean wasNull() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getByte(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short getShort(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getBoolean(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getByte(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short getShort(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
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
	public String getCursorName() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean relative(int rows) throws java.lang.UnsupportedOperationException {
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
	public int getFetchSize() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getConcurrency() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean rowUpdated() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rowInserted() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rowDeleted() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateNull(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoolean(int columnIndex, boolean x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateByte(int columnIndex, byte x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShort(int columnIndex, short x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInt(int columnIndex, int x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLong(int columnIndex, long x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFloat(int columnIndex, float x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDouble(int columnIndex, double x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateString(int columnIndex, String x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBytes(int columnIndex, byte[] x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDate(int columnIndex, Date x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTime(int columnIndex, Time x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(int columnIndex, Object x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNull(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoolean(String columnLabel, boolean x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateByte(String columnLabel, byte x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShort(String columnLabel, short x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInt(String columnLabel, int x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLong(String columnLabel, long x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFloat(String columnLabel, float x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDouble(String columnLabel, double x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateString(String columnLabel, String x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBytes(String columnLabel, byte[] x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDate(String columnLabel, Date x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTime(String columnLabel, Time x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateObject(String columnLabel, Object x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void refreshRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelRowUpdates() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveToInsertRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveToCurrentRow() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array getArray(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array getArray(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRef(int columnIndex, Ref x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRef(String columnLabel, Ref x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(int columnIndex, Blob x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(String columnLabel, Blob x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(int columnIndex, Clob x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String columnLabel, Clob x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArray(int columnIndex, Array x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateArray(String columnLabel, Array x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public RowId getRowId(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getHoldability() throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateNString(int columnIndex, String nString) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNString(String columnLabel, String nString) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public NClob getNClob(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob getNClob(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNString(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String columnLabel, Reader reader) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNClob(String columnLabel, Reader reader) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws java.lang.UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
