// ORM class for table 'Occupations'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Dec 21 15:38:28 CET 2015
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

public class Occupations extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String occupation;
  public String get_occupation() {
    return occupation;
  }
  public void set_occupation(String occupation) {
    this.occupation = occupation;
  }
  public Occupations with_occupation(String occupation) {
    this.occupation = occupation;
    return this;
  }
  private Integer min_salary;
  public Integer get_min_salary() {
    return min_salary;
  }
  public void set_min_salary(Integer min_salary) {
    this.min_salary = min_salary;
  }
  public Occupations with_min_salary(Integer min_salary) {
    this.min_salary = min_salary;
    return this;
  }
  private Integer max_salary;
  public Integer get_max_salary() {
    return max_salary;
  }
  public void set_max_salary(Integer max_salary) {
    this.max_salary = max_salary;
  }
  public Occupations with_max_salary(Integer max_salary) {
    this.max_salary = max_salary;
    return this;
  }
  private Integer median_salary;
  public Integer get_median_salary() {
    return median_salary;
  }
  public void set_median_salary(Integer median_salary) {
    this.median_salary = median_salary;
  }
  public Occupations with_median_salary(Integer median_salary) {
    this.median_salary = median_salary;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Occupations)) {
      return false;
    }
    Occupations that = (Occupations) o;
    boolean equal = true;
    equal = equal && (this.occupation == null ? that.occupation == null : this.occupation.equals(that.occupation));
    equal = equal && (this.min_salary == null ? that.min_salary == null : this.min_salary.equals(that.min_salary));
    equal = equal && (this.max_salary == null ? that.max_salary == null : this.max_salary.equals(that.max_salary));
    equal = equal && (this.median_salary == null ? that.median_salary == null : this.median_salary.equals(that.median_salary));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Occupations)) {
      return false;
    }
    Occupations that = (Occupations) o;
    boolean equal = true;
    equal = equal && (this.occupation == null ? that.occupation == null : this.occupation.equals(that.occupation));
    equal = equal && (this.min_salary == null ? that.min_salary == null : this.min_salary.equals(that.min_salary));
    equal = equal && (this.max_salary == null ? that.max_salary == null : this.max_salary.equals(that.max_salary));
    equal = equal && (this.median_salary == null ? that.median_salary == null : this.median_salary.equals(that.median_salary));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.occupation = JdbcWritableBridge.readString(1, __dbResults);
    this.min_salary = JdbcWritableBridge.readInteger(2, __dbResults);
    this.max_salary = JdbcWritableBridge.readInteger(3, __dbResults);
    this.median_salary = JdbcWritableBridge.readInteger(4, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.occupation = JdbcWritableBridge.readString(1, __dbResults);
    this.min_salary = JdbcWritableBridge.readInteger(2, __dbResults);
    this.max_salary = JdbcWritableBridge.readInteger(3, __dbResults);
    this.median_salary = JdbcWritableBridge.readInteger(4, __dbResults);
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
    JdbcWritableBridge.writeString(occupation, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(min_salary, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(max_salary, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(median_salary, 4 + __off, 4, __dbStmt);
    return 4;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(occupation, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(min_salary, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(max_salary, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(median_salary, 4 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.occupation = null;
    } else {
    this.occupation = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.min_salary = null;
    } else {
    this.min_salary = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.max_salary = null;
    } else {
    this.max_salary = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.median_salary = null;
    } else {
    this.median_salary = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.occupation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, occupation);
    }
    if (null == this.min_salary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.min_salary);
    }
    if (null == this.max_salary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.max_salary);
    }
    if (null == this.median_salary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.median_salary);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.occupation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, occupation);
    }
    if (null == this.min_salary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.min_salary);
    }
    if (null == this.max_salary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.max_salary);
    }
    if (null == this.median_salary) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.median_salary);
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
    __sb.append(FieldFormatter.escapeAndEnclose(occupation==null?"null":occupation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(min_salary==null?"null":"" + min_salary, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(max_salary==null?"null":"" + max_salary, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(median_salary==null?"null":"" + median_salary, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(occupation==null?"null":occupation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(min_salary==null?"null":"" + min_salary, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(max_salary==null?"null":"" + max_salary, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(median_salary==null?"null":"" + median_salary, delimiters));
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
    if (__cur_str.equals("null")) { this.occupation = null; } else {
      this.occupation = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.min_salary = null; } else {
      this.min_salary = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.max_salary = null; } else {
      this.max_salary = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.median_salary = null; } else {
      this.median_salary = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.occupation = null; } else {
      this.occupation = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.min_salary = null; } else {
      this.min_salary = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.max_salary = null; } else {
      this.max_salary = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.median_salary = null; } else {
      this.median_salary = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    Occupations o = (Occupations) super.clone();
    return o;
  }

  public void clone0(Occupations o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("occupation", this.occupation);
    __sqoop$field_map.put("min_salary", this.min_salary);
    __sqoop$field_map.put("max_salary", this.max_salary);
    __sqoop$field_map.put("median_salary", this.median_salary);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("occupation", this.occupation);
    __sqoop$field_map.put("min_salary", this.min_salary);
    __sqoop$field_map.put("max_salary", this.max_salary);
    __sqoop$field_map.put("median_salary", this.median_salary);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("occupation".equals(__fieldName)) {
      this.occupation = (String) __fieldVal;
    }
    else    if ("min_salary".equals(__fieldName)) {
      this.min_salary = (Integer) __fieldVal;
    }
    else    if ("max_salary".equals(__fieldName)) {
      this.max_salary = (Integer) __fieldVal;
    }
    else    if ("median_salary".equals(__fieldName)) {
      this.median_salary = (Integer) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("occupation".equals(__fieldName)) {
      this.occupation = (String) __fieldVal;
      return true;
    }
    else    if ("min_salary".equals(__fieldName)) {
      this.min_salary = (Integer) __fieldVal;
      return true;
    }
    else    if ("max_salary".equals(__fieldName)) {
      this.max_salary = (Integer) __fieldVal;
      return true;
    }
    else    if ("median_salary".equals(__fieldName)) {
      this.median_salary = (Integer) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
