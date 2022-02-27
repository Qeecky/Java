package protocols.ftp;

import javax.net.SocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FTP {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username");
        String from = scanner.nextLine();
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        FTP ftp = new FTP(from, password);
        System.out.println("What do u want to do?");
        System.out.println(ftp.menu);
        String command = scanner.nextLine();
        while (!command.equals("Q")) {
            switch (command) {
                case "L" : {
                    ftp.createDataPipe();
                    //ftp.doCommand("PORT 192,168,0,18,188,231");
                    ftp.doCommand("LIST");
                    System.out.println(ftp.readLine());
                    String[] responce = ftp.getDataMultilineResponse();
                    for (int i = 0; i < responce.length; i++) {
                        System.out.println(responce[i]);
                    }
                    break;
                }
                case "C" : {
                    System.out.println("enter the name of directory");
                    String dir = scanner.nextLine();
                    ftp.doCommand("CWD " + dir);
                    break;
                }
                case "M" : {
                    System.out.println("enter the name of directory");
                    String dir = scanner.nextLine();
                    ftp.doCommand("MKD " + dir);
                    break;
                }
                case "D" : {
                    System.out.println("enter the name of file");
                    String fileName = scanner.nextLine();
                    ftp.doCommand("DELE " + fileName);
                    //ftp.readLine();
                    break;
                }
                case "Rmd" : {
                    System.out.println("enter the name of directory");
                    String dir = scanner.nextLine();
                    ftp.doCommand("RMD " + dir);
                    break;
                }
                case "Ren" : {
                    System.out.println("enter the name of file");
                    String fileName = scanner.nextLine();
                    ftp.doCommand("RNFR " + fileName);
                    System.out.println("enter the NEW name of file");
                    fileName = scanner.nextLine();
                    ftp.doCommand("RNTO " + fileName);
                    break;
                }
                case "S" : {
                    System.out.println("enter the name of file and path");
                    String fileName = scanner.nextLine();
                    ftp.createDataPipe();
                    System.out.println("enter the name of file");
                    String fileNameS = scanner.nextLine();

                    LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
                    String line = null;
                    ArrayList<String> list = new ArrayList<String>();
                    while ((line = reader.readLine()) !=null) {
                        list.add(line);
                        //System.out.println(line);
                    }
                    String[] response1 = new String[list.size()];
                    list.toArray(response1);
                    ftp.push(response1);
                    ftp.doCommand("STOR " + fileNameS);
                }
                case "P" : {
                    ftp.doCommand("PWD");
                    break;
                }
                case "Retr" : {
                    System.out.println("enter the name of file");
                    String fileName = scanner.nextLine();
                    String reads = ftp.doCommand("SIZE " + fileName);
                    reads = reads.substring(4);
                    System.out.println(reads);
                    int read = Integer.parseInt(reads);
                    System.out.println("enter the name of file on ur machine");
                    String fileNameM = scanner.nextLine();
                    ftp.createDataPipe();
                    ftp.doCommand("RETR " + fileName);
                    ftp.readLine();
                    FileOutputStream fileOutputStream = new FileOutputStream(fileNameM);
                    //String line = ftp.readDataLine();
                    String[] responce = ftp.getDataFileMultilineResponse();
                    for (int i = 0; i < responce.length; i++) {
                        fileOutputStream.write(responce[i].getBytes());
                        fileOutputStream.write("\n".getBytes());
                    }
                    break;
                }
                case "read": {
                    ftp.readLine();
                    break;
                }
                default: {
                    System.out.println("Error. Try again");
                    break;
                }
            }
            System.out.println(ftp.menu);
            command = scanner.nextLine();
        }
        ftp.doCommand("QUIT");
    }

    private InetAddress mailHost;
    private InetAddress localhost;
    private BufferedReader in;
    private BufferedReader inData;
    private PrintWriter out;
    private PrintWriter outData;
    public String menu;
    //private SSLSocket Pipe;
    Socket pipe;
    Socket dataPipe;
    FTP(String username, String password) throws IOException {
        menu = "Press S to do command STOR\nPress L to do command LIST\nPress D to do command DELE\nPress Retr to do command RETR\nPress C to do command CWD\nPress M to do command MKD\nPress Rmd to do command RMD\nPress Ren to rename file\nPress Q to do command QUIT\n";
        InputStream inn;
        OutputStream outt;
        pipe = (Socket) ((SocketFactory) SocketFactory.getDefault()).createSocket(InetAddress.getByName("192.168.0.18"), 21);
        inn = pipe.getInputStream();
        outt = pipe.getOutputStream();
        in = new BufferedReader(new InputStreamReader(inn));
        out = new PrintWriter(new OutputStreamWriter(outt), true);
        System.out.println(in.readLine());
        doCommand("USER " + username);
        doCommand("PASS " + password);
    }
    public void createDataPipe() throws IOException {
        String dataPort = doCommand("PASV");
        String s1 = dataPort.substring(0, dataPort.lastIndexOf(','));
        String s2 = dataPort.substring(dataPort.lastIndexOf(',') + 1, dataPort.length() - 2);
        s1 = s1.substring(s1.lastIndexOf(',') + 1, s1.length());
        int dPort = Integer.parseInt(s1) * 256 + Integer.parseInt(s2);
        dataPipe = (Socket) ((SocketFactory) SocketFactory.getDefault()).createSocket(InetAddress.getByName("192.168.0.18"), dPort);
        InputStream innData = dataPipe.getInputStream();
        OutputStream outtData = dataPipe.getOutputStream();
        inData = new BufferedReader(new InputStreamReader(innData));
        outData = new PrintWriter(new OutputStreamWriter(outtData), true);
    }
    public String doCommand(String command) throws IOException {
        out.println(command);
        out.flush();
        String recieve = in.readLine();
        System.out.println(recieve);
        return recieve;
    }
    public void push (String[] file) throws IOException {
        for (int i = 0; i < file.length; i++) {
            outData.println(file[i]);
        }
        outData.flush();
        outData.close();
    }

    protected String[] getDataMultilineResponse() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        while (true) {
            String line = inData.readLine();
            if (line == null) {
                break;
            }
            if (line.equals("Connection closed by foreign host.")) {
                break;
            }
            lines.add(line);
        }
        String response[] = new String[lines.size()];
        lines.toArray(response);
        return response;
    }
    protected String[] getDataFileMultilineResponse() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        while (true) {
            String line = inData.readLine();
            if (line == null) {
                break;
            }
            if (line.equals("226 Closing data connection.")) {
                break;
            }
            lines.add(line);
        }
        String response[] = new String[lines.size()];
        lines.toArray(response);
        return response;
    }
    public String readLine() throws IOException {
        return in.readLine();
    }
}