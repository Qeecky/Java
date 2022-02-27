package lab5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class lab5_1 {
    //byte[] b = {1, 2 ,4, 5};
    //byte[] a = new byte[10];
    public static void main(String[] args) throws IOException {
        byte[] bytesToWrite = new byte[2000000]; //что записываем
        //int[] bytesToWrite10;
        for (int i = 1999999; i >= 0; i--) {
            bytesToWrite[i] = (byte)i;
        }

        byte[] bytesReaded =new byte[2000000]; //куда считываем
        String fileName ="C:/Users/Maxim/source/lab1/src/lab5/test.bin";
        try {
            FileOutputStream outFile =new FileOutputStream(fileName);
            long timeStart = System.currentTimeMillis();
            outFile.write(bytesToWrite); //запись в файл
            long timeFinished = System.currentTimeMillis();
            System.out.println(timeFinished - timeStart);
            outFile.close();
            FileInputStream inFile =new FileInputStream(fileName);
            int bytesAvailable =inFile.available(); //сколько можно считать
            //System.out.println(bytesAvailable);
            timeStart = System.currentTimeMillis();
            int count = inFile.read(bytesReaded,0,bytesAvailable);
            timeFinished = System.currentTimeMillis();
            System.out.println(timeFinished - timeStart);
            //System.out.println(count);
            inFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести запись в файл:"+fileName);}
        catch (IOException e){
            System.out.println("Ошибка ввода/вывода:"+e.toString());}
//        for (byte x: bytesReaded) {
//            System.out.println(x);
//        }
    }
}
