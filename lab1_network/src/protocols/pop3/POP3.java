package protocols.pop3;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;

public class POP3 {
    public static void main(String[] args) throws IOException, UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username");
        String from = scanner.nextLine();
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        String mailHost = "pop.gmail.com";
        POP3 mail = new POP3(mailHost);
        mail.connectToServer(from, password);
        System.out.println("What do u want to do?");
        System.out.println(mail.menu);
        String command = scanner.nextLine();
        while (!command.equals("Q")) {
            switch (command) {
                case "L" : {
                    System.out.println("Enter the number of message");
                    String msg = scanner.nextLine();
                    //mail.doCommand("LIST");
                    if (msg.equals("")) {
                         mail.doCommand("LIST");
                         String[] responce = mail.getMultilineResponse();
                         for (int i = 0; i < responce.length; i++) {
                             System.out.println(responce[i]);
                         }
                    }
                    else {
                        mail.doCommand("LIST " + msg.toString());
                    }
                    break;
                }
                case "D" : {
                    System.out.println("Enter the number of message");
                    String msg = scanner.nextLine();
                    //mail.doCommand("LIST");
                    if (msg.equals("")) {
                        System.out.println("Enter the number (necessarily)");
                    }
                    else {
                        mail.doCommand("DELE " + msg.toString());
                    }
                    break;
                }
                case "S" : {
                    mail.doCommand("STAT");
                    break;
                }
                case "Re" : {
                    System.out.println("Enter the number of message");
                    String msg = scanner.nextLine();
                    //mail.doCommand("LIST");
                    if (msg.equals("")) {
                        System.out.println("Enter the number (necessarily)");
                    }
                    else {

                        mail.doCommand("RETR " + msg);
                        String[] messageLines = mail.getMultilineResponse();
                        StringBuffer message = new StringBuffer();
                        for (int i=0; i<messageLines.length; i++) {
                            message.append(messageLines[i]);
                            message.append("\n");
                        }
                        System.out.println(new String(message));
//                        for (int i = 0; i < messageLines.length; i++) {
//                            System.out.println(messageLines[i]);
//                        }
                    }
                    break;
                }
                case "N" : {
                    mail.doCommand("NOOP");
                    break;
                }
                case "Rs" : {
                    mail.doCommand("RSET");
                    break;
                }
                case "T" : {
                    System.out.println("Enter the number of message");
                    String msg = scanner.nextLine();
                    System.out.println("Enter the number of strings in message");
                    //String str = scanner.nextLine();
                    int numStr = scanner.nextInt();
                    numStr += 3;
                    String str = String.valueOf(numStr);
                    //mail.doCommand("LIST");
                    if (msg.equals("") || str.equals("")) {
                        System.out.println("Enter the number and strings (necessarily)");
                    }
                    else {
                        mail.doCommand("TOP " + msg + " " + str);
                        //System.out.println(str);
                        String[] messageLines = mail.getMultilineResponse();
//                    StringBuffer message = new StringBuffer();
//                    for (int i=0; i<messageLines.length; i++) {
//                        message.append(messageLines[i]);
//                        message.append("\n");
//                    }
//                    System.out.println(new String(message));
                        for (int i = 0; i < messageLines.length; i++) {
                            System.out.println(messageLines[i]);
                        }
                        break;
                    }
                }
                default: {
                    System.out.println("Error. Try again");
                    break;
                }
            }
            System.out.println(mail.menu);
            command = scanner.nextLine();
        }
        mail.doCommand("QUIT");
    }


    private static final int POP3_PORT = 995;
    private InetAddress mailHost;
    private InetAddress localhost;
    private BufferedReader in;
    private PrintWriter out;
    private SSLSocket Pipe;
    public String menu;
    public POP3(String host) throws UnknownHostException {
        mailHost = InetAddress.getByName(host);
        menu = "Press S to do command STAT\nPress L to do command LIST\nPress D to do command DELE\nPress N to do command NOOP\nPress Re to do command RETR\nPress Rs to do command RSET\nPress T to do command TOP\nPress Q to do command QUIT\n";
    }
    public boolean connectToServer(String from, String password) throws IOException {

        InputStream inn;
        OutputStream outt;
        Pipe = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(InetAddress.getByName("pop.gmail.com"), 995);
        if (Pipe == null) {
            return false;
        }

        inn = Pipe.getInputStream();
        outt = Pipe.getOutputStream();
        in = new BufferedReader(new InputStreamReader(inn));
        out = new PrintWriter(new OutputStreamWriter(outt), true);
        System.out.println(in.readLine());
        if (inn == null || outt == null) {
            System.out.println("Failed to open streams to socket.");
            return false;
        }
        //byte[] bytesEncodedFrom = Base64.getEncoder().encode(from.getBytes());
        //byte[] bytesEncodedPas = Base64.getEncoder().encode(password.getBytes());
        doCommand("USER " + from);
        doCommand("PASS " + password);
        return true;

    }
    public boolean doCommand(String command) throws IOException {
        out.println(command);
        out.flush();
        System.out.println(new String(in.readLine()));
        return true;
    }
    protected String[] getMultilineResponse() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        while (true) {
            String line = in.readLine();
            if (line == null) {
                throw new IOException("Server unawares closed the connection.");
                //break;
            }
            if (line.equals(".")) {
                break;
            }

            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                line = line.substring(1);
            }
            //byte[] bytesDecoded = Base64.getDecoder().decode(line);
            lines.add(line);
            //lines.add(new String(bytesDecoded));
        }
        String response[] = new String[lines.size()];
        lines.toArray(response);
        return response;
    }
}

