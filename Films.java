// ORM class for table 'Films'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Dec 21 15:30:49 CET 2015
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

public class Films extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Integer id_item;
  public Integer get_id_item() {
    return id_item;
  }
  public void set_id_item(Integer id_item) {
    this.id_item = id_item;
  }
  public Films with_id_item(Integer id_item) {
    this.id_item = id_item;
    return this;
  }
  private String movie_title;
  public String get_movie_title() {
    return movie_title;
  }
  public void set_movie_title(String movie_title) {
    this.movie_title = movie_title;
  }
  public Films with_movie_title(String movie_title) {
    this.movie_title = movie_title;
    return this;
  }
  private Long release_date;
  public Long get_release_date() {
    return release_date;
  }
  public void set_release_date(Long release_date) {
    this.release_date = release_date;
  }
  public Films with_release_date(Long release_date) {
    this.release_date = release_date;
    return this;
  }
  private String IMDB_URL;
  public String get_IMDB_URL() {
    return IMDB_URL;
  }
  public void set_IMDB_URL(String IMDB_URL) {
    this.IMDB_URL = IMDB_URL;
  }
  public Films with_IMDB_URL(String IMDB_URL) {
    this.IMDB_URL = IMDB_URL;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Films)) {
      return false;
    }
    Films that = (Films) o;
    boolean equal = true;
    equal = equal && (this.id_item == null ? that.id_item == null : this.id_item.equals(that.id_item));
    equal = equal && (this.movie_title == null ? that.movie_title == null : this.movie_title.equals(that.movie_title));
    equal = equal && (this.release_date == null ? that.release_date == null : this.release_date.equals(that.release_date));
    equal = equal && (this.IMDB_URL == null ? that.IMDB_URL == null : this.IMDB_URL.equals(that.IMDB_URL));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Films)) {
      return false;
    }
    Films that = (Films) o;
    boolean equal = true;
    equal = equal && (this.id_item == null ? that.id_item == null : this.id_item.equals(that.id_item));
    equal = equal && (this.movie_title == null ? that.movie_title == null : this.movie_title.equals(that.movie_title));
    equal = equal && (this.release_date == null ? that.release_date == null : this.release_date.equals(that.release_date));
    equal = equal && (this.IMDB_URL == null ? that.IMDB_URL == null : this.IMDB_URL.equals(that.IMDB_URL));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id_item = JdbcWritableBridge.readInteger(1, __dbResults);
    this.movie_title = JdbcWritableBridge.readString(2, __dbResults);
    this.release_date = JdbcWritableBridge.readLong(3, __dbResults);
    this.IMDB_URL = JdbcWritableBridge.readString(4, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id_item = JdbcWritableBridge.readInteger(1, __dbResults);
    this.movie_title = JdbcWritableBridge.readString(2, __dbResults);
    this.release_date = JdbcWritableBridge.readLong(3, __dbResults);
    this.IMDB_URL = JdbcWritableBridge.readString(4, __dbResults);
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
    JdbcWritableBridge.writeInteger(id_item, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(movie_title, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(release_date, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(IMDB_URL, 4 + __off, 12, __dbStmt);
    return 4;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id_item, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(movie_title, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(release_date, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(IMDB_URL, 4 + __off, 12, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id_item = null;
    } else {
    this.id_item = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.movie_title = null;
    } else {
    this.movie_title = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.release_date = null;
    } else {
    this.release_date = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.IMDB_URL = null;
    } else {
    this.IMDB_URL = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id_item) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_item);
    }
    if (null == this.movie_title) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, movie_title);
    }
    if (null == this.release_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.release_date);
    }
    if (null == this.IMDB_URL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, IMDB_URL);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id_item) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_item);
    }
    if (null == this.movie_title) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, movie_title);
    }
    if (null == this.release_date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.release_date);
    }
    if (null == this.IMDB_URL) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, IMDB_URL);
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
    __sb.append(FieldFormatter.escapeAndEnclose(id_item==null?"null":"" + id_item, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(movie_title==null?"null":movie_title, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(release_date==null?"null":"" + release_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(IMDB_URL==null?"null":IMDB_URL, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id_item==null?"null":"" + id_item, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(movie_title==null?"null":movie_title, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(release_date==null?"null":"" + release_date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(IMDB_URL==null?"null":IMDB_URL, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_item = null; } else {
      this.id_item = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.movie_title = null; } else {
      this.movie_title = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.release_date = null; } else {
      this.release_date = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.IMDB_URL = null; } else {
      this.IMDB_URL = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_item = null; } else {
      this.id_item = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.movie_title = null; } else {
      this.movie_title = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.release_date = null; } else {
      this.release_date = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.IMDB_URL = null; } else {
      this.IMDB_URL = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    Films o = (Films) super.clone();
    return o;
  }

  public void clone0(Films o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("id_item", this.id_item);
    __sqoop$field_map.put("movie_title", this.movie_title);
    __sqoop$field_map.put("release_date", this.release_date);
    __sqoop$field_map.put("IMDB_URL", this.IMDB_URL);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id_item", this.id_item);
    __sqoop$field_map.put("movie_title", this.movie_title);
    __sqoop$field_map.put("release_date", this.release_date);
    __sqoop$field_map.put("IMDB_URL", this.IMDB_URL);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("id_item".equals(__fieldName)) {
      this.id_item = (Integer) __fieldVal;
    }
    else    if ("movie_title".equals(__fieldName)) {
      this.movie_title = (String) __fieldVal;
    }
    else    if ("release_date".equals(__fieldName)) {
      this.release_date = (Long) __fieldVal;
    }
    else    if ("IMDB_URL".equals(__fieldName)) {
      this.IMDB_URL = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("id_item".equals(__fieldName)) {
      this.id_item = (Integer) __fieldVal;
      return true;
    }
    else    if ("movie_title".equals(__fieldName)) {
      this.movie_title = (String) __fieldVal;
      return true;
    }
    else    if ("release_date".equals(__fieldName)) {
      this.release_date = (Long) __fieldVal;
      return true;
    }
    else    if ("IMDB_URL".equals(__fieldName)) {
      this.IMDB_URL = (String) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
