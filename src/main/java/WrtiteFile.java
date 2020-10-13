import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors


public class WrtiteFile {

    public static void write(String name , String text){
       try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter(name));
            writer.write(text);
            writer.close();
        } catch (Exception ignored) {
           System.out.println(ignored.getMessage());
       }
    }


}