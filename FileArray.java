import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class FileArray {
  protected final File file;
  private static final int MAX_VALUE = 1024;
  public static final int NUMBERS_IN_A_ROW = 5;

  public FileArray(File file) {
    this.file = file;
  }

  public FileArray(File file, int n) throws IOException {
    this.file = file;
    int[] data = new int[n];

    for (int i = 0; i < data.length; i++) {
      Random random = new Random();
      data[i] = random.nextInt(MAX_VALUE + 1);
    }
    write(data);
  }

  public void print() throws IOException {
    int[] data = read();

    int maxDigits = 0;
    for(int i=0; i<data.length; i++){
      if(countDigits(data[i])>maxDigits) maxDigits = data[i];
    }

    int spaceIndex = (int) (Math.log10(data.length - 1) + 1);
    for (int i = 0; i < (data.length / 5.0); i++) {
      if (i + 1 > (data.length / 5.0)) {
        System.out.printf("[%0" + spaceIndex + "d-%0" + spaceIndex + "d] ", i * NUMBERS_IN_A_ROW, (data.length - 1));
      }
      else {
        System.out.printf("[%0" + spaceIndex + "d-%0" + spaceIndex + "d] ", i * NUMBERS_IN_A_ROW, ((i * NUMBERS_IN_A_ROW) + 4));
      }

      int spaceElements = (int) (Math.log10(maxDigits) + 1) + 1;
      for (int j = 0; j < NUMBERS_IN_A_ROW; j++) {
        if ((i * NUMBERS_IN_A_ROW) + j < data.length) {
          System.out.printf("%" + spaceElements + "d ", data[(i * NUMBERS_IN_A_ROW) + j]);
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  private int countDigits(int n){
    int digits = 0;
    while(n != 0) {
      n /= 10;
      digits++;
    }
    return digits;
  }

  public void incrementAll() throws IOException {
    int[] data = read();

    for (int i=0; i<data.length; i++){
      data[i] += 1;
    }
    write(data);
  }

  protected int[] read() throws IOException {
    DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
    return readStream(dataInputStream);
  }

  protected void write(int[] data) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
    writeStream(dataOutputStream, data);
  }

  protected int[] readStream(InputStream InputStream) throws IOException{
    DataInputStream dataInputStream = new DataInputStream(InputStream);
    int length = dataInputStream.readInt();
    int[] data = new int[length];
    for (int i = 0; i < data.length; i++) {
      data[i] = dataInputStream.readInt();
    }
    dataInputStream.close();
    return data;
  }

  protected void writeStream(OutputStream OutputStream, int[] buffer) throws IOException {
    DataOutputStream dataOutputStream = new DataOutputStream(OutputStream);
    dataOutputStream.writeInt(buffer.length);
    for (int i = 0; i < buffer.length; i++) {
      dataOutputStream.writeInt(buffer[i]);
    }
    dataOutputStream.close();
  }
}