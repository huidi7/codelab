import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;

public class EncodingTestMain {
  public static void main(String[] args) throws IOException {
    iotest();
    encode();
}

  public static void toHex(byte[] bytes) {
    System.out.print("[" + bytes.length + "]");
    for (byte b:bytes) {
      System.out.print(" ");
      System.out.print(Integer.toHexString(0xff & b));
    }
    System.out.println();
  }
  
  public static void toHex(char[] chars) {
    System.out.print("[" + chars.length + "]");
    for (char c: chars) {
      System.out.print(" ");
      System.out.print(Integer.toHexString(c));
    }
    System.out.println();
  }

  public static void iotest() throws IOException {
    String file = "stream.txt";
    String charset = "UTF-8";
    // 写字符换转成字节流
    FileOutputStream outputStream = new FileOutputStream(file);
    OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
    try {
      writer.write("这是要保存的中文字符");
    } finally {
      writer.close();
    }
    // 读取字节转换成字符
    FileInputStream inputStream = new FileInputStream(file);
    InputStreamReader reader = new InputStreamReader(inputStream, charset);
    StringBuffer buffer = new StringBuffer();
    char[] buf = new char[64];
    int count = 0;
    try {
      while ((count = reader.read(buf)) != -1) {
        buffer.append(buf, 0, count);
      }
    } finally {
      reader.close();
    }
    System.out.println(buffer.toString());
  }
  
  public static void encode() {
    String name = "I am ¥34";
    System.out.println("string: \"" + name + "\"");
    System.out.print("char array:\t");
    toHex(name.toCharArray());
    try {
      byte[] iso8859 = name.getBytes("ISO-8859-1");
      System.out.print("ISO-8859-1:\t");
      toHex(iso8859);
      byte[] gb2312 = name.getBytes("GB2312");
      System.out.print("GB2312:\t\t");
      toHex(gb2312);
      byte[] gbk = name.getBytes("GBK");
      System.out.print("GBK:\t\t");
      toHex(gbk);
      byte[] utf16 = name.getBytes("UTF-16");
      System.out.print("UTF-16:\t\t");
      toHex(utf16);
      byte[] utf8 = name.getBytes("UTF-8");
      System.out.print("UTF-8:\t\t");
      toHex(utf8);
      byte[] def = name.getBytes();
      System.out.print("default:\t");
      toHex(def);
      String value = new String(utf16, "GBK");
      System.out.println("value: " + value);
      byte[] bvalues = value.getBytes("ISO-8859-1");
      String value2 = new String(bvalues);
      System.out.println("value2: " + value2);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
