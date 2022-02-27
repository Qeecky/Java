package protocols.smtp;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Scanner;

public class SMTP {

    public static void main(String[] args) throws IOException,
            UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        //String msgFile = "file.txt";
//          if we use login in exe ->
        System.out.println("Enter a sender");
        String from = scanner.nextLine();
        System.out.println("Enter the password");
        String password = scanner.nextLine();
//        String from = "maxlev57@gmail.com";
//        String password = "1234567";
        System.out.println("Enter the recipient");
        String to = scanner.nextLine();
        System.out.println("Enter the subject");
        String subject = scanner.nextLine();
        System.out.println("Enter the message");
        String message = scanner.nextLine();
        String mailHost = "smtp.gmail.com";

        SMTP mail = new SMTP(mailHost);
//        char[] pas = new char[password.length()];
//        for (int i = 0; i < password.length(); i++) {
//            pas[i] = password.charAt(i);
//        }
        if (mail != null) {
            if (mail.send(from, to, password, subject, message)) {
                System.out.println("Mail sent.");
            } else {
                System.out.println("Connect to SMTP server failed!");
            }
        }
        System.out.println("Done.");
    }

    //static class SMTP {
    private final static int SMTP_PORT = 25;

    InetAddress mailHost;

    InetAddress localhost;

    BufferedReader in;

    PrintWriter out;

    public SMTP(String host) throws UnknownHostException {
        mailHost = InetAddress.getByName(host);
        localhost = InetAddress.getLocalHost();
        System.out.println("mailhost = " + mailHost);
        System.out.println("localhost= " + localhost);
        System.out.println("SMTP constructor done\n");
    }

    public boolean send(String from, String to, String password, String subject, String message)
                throws IOException {
        SSLSocket smtpPipe;
        InputStream inn;
        OutputStream outt;
        //BufferedReader msg;
        //PasswordAuthentication passwordAuthentication = new PasswordAuthentication(from, password);
        //passwordAuthentication.
        //msg = new BufferedReader(msgFileReader);
        smtpPipe = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(InetAddress.getByName("smtp.gmail.com"), 465);
        if (smtpPipe == null) {
            return false;
        }

        inn = smtpPipe.getInputStream();
        outt = smtpPipe.getOutputStream();
        in = new BufferedReader(new InputStreamReader(inn));
        out = new PrintWriter(new OutputStreamWriter(outt), true);
        if (inn == null || outt == null) {
            System.out.println("Failed to open streams to socket.");
            return false;
        }
//            for entering password & login
        byte[] bytesEncodedFrom = Base64.getEncoder().encode(from.getBytes());
        byte[] bytesEncodedPas = Base64.getEncoder().encode(password.getBytes());
        String initialID = in.readLine();
        System.out.println(initialID);
        System.out.println("HELO " + localhost.getHostName());
        out.println("HELO " + localhost.getHostName());

        String welcome = in.readLine();
        System.out.println(welcome);
        //System.out.println("AUTH LOGIN");
        out.println("AUTH LOGIN");
        System.out.println(new String(in.readLine()));
            //base64 from
        //out.println("bWF4bGV2NTdAZ21haWwuY29t");
        out.println(new String(bytesEncodedFrom));
        //System.out.println(new String(in.readLine()));
        //System.out.println("pas has been entered");
        //out.println("Ump5eWpoKjE2MDE=");
        out.println(new String(bytesEncodedPas));
        //System.out.println(new String(in.readLine()));
        String senderOK = in.readLine();
        System.out.println(senderOK);
        out.println("MAIL FROM:<" + from + ">");
        System.out.println(new String(in.readLine()));
        System.out.println("MAIL From:<" + from + ">");
        out.println("RCPT TO:<" + to + ">");
        System.out.println(new String(in.readLine()));
        System.out.println("RCPT TO:<" + to + ">");
        out.println("DATA");
        //System.out.println(new String(in.readLine()));
//            out.println("MAIL FROM:<" + from + ">");
//            System.out.println("RCPT TO:<" + to + ">");
//            out.println("RCPT TO:<" + to + ">");
        String recipientOK = in.readLine();
        System.out.println(recipientOK);
        out.println("SUBJECT:" + subject);
        //System.out.println(new String(in.readLine()));
            //String isOk = in.readLine();
            //System.out.println(isOk);
        out.println(message);
        //System.out.println(new String(in.readLine()));
            //String line;
//            while ((line = msg.readLine()) != null) {
//                out.println(line);
//            }
        //System.out.println(".");
        out.println(".");
        String acceptedOK = in.readLine();
        System.out.println(acceptedOK);
        System.out.println("QUIT");
        out.println("QUIT");
        System.out.println(new String(in.readLine()));
        return true;
    }
    //}
}