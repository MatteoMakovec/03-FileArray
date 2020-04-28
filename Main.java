import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
  public static void main (String[] args) throws IOException {
    File file = new File(args[0]);
    FileArray fileArray;
    if(file.exists()){
      fileArray = new FileArray(file);
    }
    else{
      Random r = new Random();
      int n = r.nextInt((int) Math.pow(2, 5))+1;
      fileArray = new FileArray(file, n);
    }

    if (args.length == 0) {
      System.out.println("\nCommand line argument(s) are missing");
      return;
    }

    if (args.length == 1){
      System.out.println("\nYou miss some argument(s), please follow the protocol!");
    }

    else{
      for(int i=0; i<args.length; i++){
        switch(args[i]){
          case "p":   fileArray.print();
                      break;

          case "i":   fileArray.incrementAll();
                      System.out.println("Values incremented\n");
                      break;
        }
      }
    }
  }
}
