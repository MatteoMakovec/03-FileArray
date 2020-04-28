import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

public class FileArray {
  private final File file;

  public FileArray(File file) { // load an existing file
    this.file = file;
  }

  public FileArray(File file, int n) throws IOException { // create a new file with n random elements
    this.file = file;
    int[] array = new int[n];

    for (int i = 0; i < array.length; i++) {
      Random r = new Random();
      array[i] = r.nextInt((int) Math.pow(2, 10));
    }
    write(array);
  }

  public void print() throws IOException { // pretty print with at most 5 aligned elements per row
    int[] values = read();

    int maxDigits = 0;
    for(int i=0; i<values.length; i++){
      if(countDigits(values[i])>maxDigits) maxDigits = values[i];
    }

    int spaceIndex = (int) (Math.log10(values.length - 1) + 1);
    for (int i = 0; i < (values.length / 5.0); i++) {
      if (i + 1 > (values.length / 5.0)) {
        System.out.printf("[%0" + spaceIndex + "d-%0" + spaceIndex + "d] ", i * 5, (values.length - 1));
      } 
      else {
        System.out.printf("[%0" + spaceIndex + "d-%0" + spaceIndex + "d] ", i * 5, ((i * 5) + 4));
      }

      int spaceElements = (int) (Math.log10(maxDigits) + 1) + 1;
      for (int j = 0; j < 5; j++) {
        if ((i * 5) + j < values.length) {
          System.out.printf("%" + spaceElements + "d ", values[(i * 5) + j]);
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

  public void incrementAll() throws IOException { // increment all elements
    int[] array = read();

    for (int i=0; i<array.length; i++){
      array[i] += 1;
    }
    write(array);
  }

  private int[] read() throws IOException {
    FileInputStream fileInputStream = new FileInputStream(file);
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);

    int length = dataInputStream.readInt();

    int[] array = new int[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = dataInputStream.readInt();
    }
    dataInputStream.close();
    return array;
  }

  private void write(int[] array) throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
    dataOutputStream.writeInt(array.length);
    for (int i = 0; i < array.length; i++) {
      dataOutputStream.writeInt(array[i]);
    }
    dataOutputStream.close();
  }
}
