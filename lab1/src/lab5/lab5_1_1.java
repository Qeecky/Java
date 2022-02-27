package lab5;

import java.io.*;

public class lab5_1_1 {
    public static void main(String[] args) throws IOException {
        //byte[] bytesToWrite ={1,2,7,4}; //что записываем
        //byte[] bytesReaded =new byte[4]; //куда считываем
        String fileName ="C:/Users/Maxim/source/lab1/src/lab5/test1.txt";
        long timeStart = System.currentTimeMillis();
        OutputStream outStream = new BufferedOutputStream(new FileOutputStream(fileName));
        for (int i = 2000000; i >= 0; i--) {
            outStream.write(i);
        }
        outStream.close();
        long timeFinished = System.currentTimeMillis();
        System.out.println(timeFinished - timeStart);
        InputStream inStream = new BufferedInputStream(new FileInputStream(fileName));
        timeStart = System.currentTimeMillis();
        while (inStream.read() != -1) {
            //System.out.println((int)(inStream.read()));

        }
        timeFinished = System.currentTimeMillis();
        System.out.println(timeFinished - timeStart);
    }
}
