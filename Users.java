// ORM class for table 'Users'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Dec 21 15:41:57 CET 2015
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

public class Users extends SqoopRecord  implements DBWritable, Writable {
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
  public Users with_id_user(Integer id_user) {
    this.id_user = id_user;
    return this;
  }
  private Integer age;
  public Integer get_age() {
    return age;
  }
  public void set_age(Integer age) {
    this.age = age;
  }
  public Users with_age(Integer age) {
    this.age = age;
    return this;
  }
  private String gender;
  public String get_gender() {
    return gender;
  }
  public void set_gender(String gender) {
    this.gender = gender;
  }
  public Users with_gender(String gender) {
    this.gender = gender;
    return this;
  }
  private String occupation;
  public String get_occupation() {
    return occupation;
  }
  public void set_occupation(String occupation) {
    this.occupation = occupation;
  }
  public Users with_occupation(String occupation) {
    this.occupation = occupation;
    return this;
  }
  private String zip;
  public String get_zip() {
    return zip;
  }
  public void set_zip(String zip) {
    this.zip = zip;
  }
  public Users with_zip(String zip) {
    this.zip = zip;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Users)) {
      return false;
    }
    Users that = (Users) o;
    boolean equal = true;
    equal = equal && (this.id_user == null ? that.id_user == null : this.id_user.equals(that.id_user));
    equal = equal && (this.age == null ? that.age == null : this.age.equals(that.age));
    equal = equal && (this.gender == null ? that.gender == null : this.gender.equals(that.gender));
    equal = equal && (this.occupation == null ? that.occupation == null : this.occupation.equals(that.occupation));
    equal = equal && (this.zip == null ? that.zip == null : this.zip.equals(that.zip));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Users)) {
      return false;
    }
    Users that = (Users) o;
    boolean equal = true;
    equal = equal && (this.id_user == null ? that.id_user == null : this.id_user.equals(that.id_user));
    equal = equal && (this.age == null ? that.age == null : this.age.equals(that.age));
    equal = equal && (this.gender == null ? that.gender == null : this.gender.equals(that.gender));
    equal = equal && (this.occupation == null ? that.occupation == null : this.occupation.equals(that.occupation));
    equal = equal && (this.zip == null ? that.zip == null : this.zip.equals(that.zip));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id_user = JdbcWritableBridge.readInteger(1, __dbResults);
    this.age = JdbcWritableBridge.readInteger(2, __dbResults);
    this.gender = JdbcWritableBridge.readString(3, __dbResults);
    this.occupation = JdbcWritableBridge.readString(4, __dbResults);
    this.zip = JdbcWritableBridge.readString(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id_user = JdbcWritableBridge.readInteger(1, __dbResults);
    this.age = JdbcWritableBridge.readInteger(2, __dbResults);
    this.gender = JdbcWritableBridge.readString(3, __dbResults);
    this.occupation = JdbcWritableBridge.readString(4, __dbResults);
    this.zip = JdbcWritableBridge.readString(5, __dbResults);
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
    JdbcWritableBridge.writeInteger(age, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(gender, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(occupation, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(zip, 5 + __off, 12, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id_user, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(age, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(gender, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(occupation, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(zip, 5 + __off, 12, __dbStmt);
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
        this.age = null;
    } else {
    this.age = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.gender = null;
    } else {
    this.gender = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.occupation = null;
    } else {
    this.occupation = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.zip = null;
    } else {
    this.zip = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_user);
    }
    if (null == this.age) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.age);
    }
    if (null == this.gender) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, gender);
    }
    if (null == this.occupation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, occupation);
    }
    if (null == this.zip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, zip);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id_user) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_user);
    }
    if (null == this.age) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.age);
    }
    if (null == this.gender) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, gender);
    }
    if (null == this.occupation) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, occupation);
    }
    if (null == this.zip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, zip);
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
    __sb.append(FieldFormatter.escapeAndEnclose(age==null?"null":"" + age, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(gender==null?"null":gender, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(occupation==null?"null":occupation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(zip==null?"null":zip, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id_user==null?"null":"" + id_user, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(age==null?"null":"" + age, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(gender==null?"null":gender, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(occupation==null?"null":occupation, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(zip==null?"null":zip, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.age = null; } else {
      this.age = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.gender = null; } else {
      this.gender = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.occupation = null; } else {
      this.occupation = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.zip = null; } else {
      this.zip = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.age = null; } else {
      this.age = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.gender = null; } else {
      this.gender = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.occupation = null; } else {
      this.occupation = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.zip = null; } else {
      this.zip = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    Users o = (Users) super.clone();
    return o;
  }

  public void clone0(Users o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("id_user", this.id_user);
    __sqoop$field_map.put("age", this.age);
    __sqoop$field_map.put("gender", this.gender);
    __sqoop$field_map.put("occupation", this.occupation);
    __sqoop$field_map.put("zip", this.zip);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id_user", this.id_user);
    __sqoop$field_map.put("age", this.age);
    __sqoop$field_map.put("gender", this.gender);
    __sqoop$field_map.put("occupation", this.occupation);
    __sqoop$field_map.put("zip", this.zip);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("id_user".equals(__fieldName)) {
      this.id_user = (Integer) __fieldVal;
    }
    else    if ("age".equals(__fieldName)) {
      this.age = (Integer) __fieldVal;
    }
    else    if ("gender".equals(__fieldName)) {
      this.gender = (String) __fieldVal;
    }
    else    if ("occupation".equals(__fieldName)) {
      this.occupation = (String) __fieldVal;
    }
    else    if ("zip".equals(__fieldName)) {
      this.zip = (String) __fieldVal;
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
    else    if ("age".equals(__fieldName)) {
      this.age = (Integer) __fieldVal;
      return true;
    }
    else    if ("gender".equals(__fieldName)) {
      this.gender = (String) __fieldVal;
      return true;
    }
    else    if ("occupation".equals(__fieldName)) {
      this.occupation = (String) __fieldVal;
      return true;
    }
    else    if ("zip".equals(__fieldName)) {
      this.zip = (String) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
