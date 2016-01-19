// ORM class for table 'Ratings'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Dec 21 15:40:42 CET 2015
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ratings extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Integer id_user;
  public Integer get_id_user() {
    return id_user;
  }
  public void set_id_user(Integer id_user) {
    this.id_user = id_user;
  }
  public Ratings with_id_user(Integer id_user) {
    this.id_user = id_user;
    return this;
  }
  private Integer id_film;
  public Integer get_id_film() {
    return id_film;
  }
  public void set_id_film(Integer id_film) {
    this.id_film = id_film;
  }
  public Ratings with_id_film(Integer id_film) {
    this.id_film = id_film;
    return this;
  }
  private Integer rating;
  public Integer get_rating() {
    return rating;
  }
  public void set_rating(Integer rating) {
    this.rating = rating;
  }
  public Ratings with_rating(Integer rating) {
    this.rating = rating;
    return this;
  }
  private Long momentum;
  public Long get_momentum() {
    return momentum;
  }
  public void set_momentum(Long momentum) {
    this.momentum = momentum;
  }
  public Ratings with_momentum(Long momentum) {
    this.momentum = momentum;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Ratings)) {
      return false;
    }
    Ratings that = (Ratings) o;
    boolean equal = true;
    equal = equal && (this.id_user == null ? that.id_user == null : this.id_user.equals(that.id_user));
    equal = equal && (this.id_film == null ? that.id_film == null : this.id_film.equals(that.id_film));
    equal = equal && (this.rating == null ? that.rating == null : this.rating.equals(that.rating));
    equal = equal && (this.momentum == null ? that.momentum == null : this.momentum.equals(that.momentum));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Ratings)) {
      return false;
    }
    Ratings that = (Ratings) o;
    boolean equal = true;
    equal = equal && (this.id_user == null ? that.id_user == null : this.id_user.equals(that.id_user));
    equal = equal && (this.id_film == null ? that.id_film == null : this.id_film.equals(that.id_film));
    equal = equal && (this.rating == null ? that.rating == null : this.rating.equals(that.rating));
    equal = equal && (this.momentum == null ? that.momentum == null : this.momentum.equals(that.momentum));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id_user = JdbcWritableBridge.readInteger(1, __dbResults);
    this.id_film = JdbcWritableBridge.readInteger(2, __dbResults);
    this.rating = JdbcWritableBridge.readInteger(3, __dbResults);
    this.momentum = JdbcWritableBridge.readLong(4, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id_user = JdbcWritableBridge.readInteger(1, __dbResults);
    this.id_film = JdbcWritableBridge.readInteger(2, __dbResults);
    this.rating = JdbcWritableBridge.readInteger(3, __dbResults);
    this.momentum = JdbcWritableBridge.readLong(4, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id_user, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(id_film, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(rating, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeLong(momentum, 4 + __off, -5, __dbStmt);
    return 4;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id_user, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(id_film, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(rating, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeLong(momentum, 4 + __off, -5, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id_user = null;
    } else {
    this.id_user = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.id_film = null;
    } else {
    this.id_film = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.rating = null;
    } else {
    this.rating = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.momentum = null;
    } else {
    this.momentum = Long.valueOf(__dataIn.readLong());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_user);
    }
    if (null == this.id_film) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_film);
    }
    if (null == this.rating) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.rating);
    }
    if (null == this.momentum) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.momentum);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_user);
    }
    if (null == this.id_film) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_film);
    }
    if (null == this.rating) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.rating);
    }
    if (null == this.momentum) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.momentum);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(id_user==null?"null":"" + id_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_film==null?"null":"" + id_film, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rating==null?"null":"" + rating, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(momentum==null?"null":"" + momentum, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id_user==null?"null":"" + id_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_film==null?"null":"" + id_film, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(rating==null?"null":"" + rating, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(momentum==null?"null":"" + momentum, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_user = null; } else {
      this.id_user = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_film = null; } else {
      this.id_film = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rating = null; } else {
      this.rating = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.momentum = null; } else {
      this.momentum = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_user = null; } else {
      this.id_user = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_film = null; } else {
      this.id_film = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.rating = null; } else {
      this.rating = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.momentum = null; } else {
      this.momentum = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    Ratings o = (Ratings) super.clone();
    return o;
  }

  public void clone0(Ratings o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("id_user", this.id_user);
    __sqoop$field_map.put("id_film", this.id_film);
    __sqoop$field_map.put("rating", this.rating);
    __sqoop$field_map.put("momentum", this.momentum);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id_user", this.id_user);
    __sqoop$field_map.put("id_film", this.id_film);
    __sqoop$field_map.put("rating", this.rating);
    __sqoop$field_map.put("momentum", this.momentum);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("id_user".equals(__fieldName)) {
      this.id_user = (Integer) __fieldVal;
    }
    else    if ("id_film".equals(__fieldName)) {
      this.id_film = (Integer) __fieldVal;
    }
    else    if ("rating".equals(__fieldName)) {
      this.rating = (Integer) __fieldVal;
    }
    else    if ("momentum".equals(__fieldName)) {
      this.momentum = (Long) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("id_user".equals(__fieldName)) {
      this.id_user = (Integer) __fieldVal;
      return true;
    }
    else    if ("id_film".equals(__fieldName)) {
      this.id_film = (Integer) __fieldVal;
      return true;
    }
    else    if ("rating".equals(__fieldName)) {
      this.rating = (Integer) __fieldVal;
      return true;
    }
    else    if ("momentum".equals(__fieldName)) {
      this.momentum = (Long) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
