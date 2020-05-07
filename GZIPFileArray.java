import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.IOException;

public class GZIPFileArray extends FileArray{

  public GZIPFileArray(File file) {
    super(file);
  }

  public GZIPFileArray(File file, int n) throws IOException {
    super(file, n);
  }

  protected int[] read() throws IOException{
    return readStream(new GZIPInputStream(new FileInputStream(file)));
  }

  protected void write(int[] buffer) throws IOException{
    writeStream(new GZIPOutputStream(new FileOutputStream(file)), buffer);
  }
}