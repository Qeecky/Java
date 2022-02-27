//package com.company;
//
//import javax.net.ssl.SSLSocket;
//import javax.net.ssl.SSLSocketFactory;
//import java.io.*;
//import java.net.InetAddress;
//import java.net.PasswordAuthentication;
//import java.net.UnknownHostException;
//import java.util.Scanner;
//
//public class SMTP {
//
//    public static void main(String[] args) throws IOException,
//            UnknownHostException {
//        Scanner scanner = new Scanner(System.in);
//        String msgFile = "file.txt";
//        String from = scanner.nextLine();
//        System.out.println("Enter the recipient");
//        String to = scanner.nextLine();
//        String mailHost = "smtp.gmail.com";
//        String password = "Rjyyjh*1601";
//        SMTP mail = new SMTP(mailHost);
////        char[] pas = new char[password.length()];
////        for (int i = 0; i < password.length(); i++) {
////            pas[i] = password.charAt(i);
////        }
//        if (mail != null) {
//            if (mail.send(new FileReader(msgFile), from, to, password)) {
//                System.out.println("Mail sent.");
//            } else {
//                System.out.println("Connect to SMTP server failed!");
//            }
//        }
//        System.out.println("Done.");
//    }
//
//    //static class SMTP {
//    private final static int SMTP_PORT = 25;
//
//    InetAddress mailHost;
//
//    InetAddress localhost;
//
//    BufferedReader in;
//
//    PrintWriter out;
//
//    public SMTP(String host) throws UnknownHostException {
//        mailHost = InetAddress.getByName(host);
//        localhost = InetAddress.getLocalHost();
//        System.out.println("mailhost = " + mailHost);
//        System.out.println("localhost= " + localhost);
//        System.out.println("SMTP constructor done\n");
//    }
//
//    public boolean send(FileReader msgFileReader, String from, String to, String password)
//            throws IOException {
//        SSLSocket smtpPipe;
//        InputStream inn;
//        OutputStream outt;
//        BufferedReader msg;
//        //PasswordAuthentication passwordAuthentication = new PasswordAuthentication(from, password);
//        //passwordAuthentication.
//        msg = new BufferedReader(msgFileReader);
//        smtpPipe = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(InetAddress.getByName("smtp.gmail.com"), 465);
//        if (smtpPipe == null) {
//            return false;
//        }
//
//        inn = smtpPipe.getInputStream();
//        outt = smtpPipe.getOutputStream();
//        in = new BufferedReader(new InputStreamReader(inn));
//        out = new PrintWriter(new OutputStreamWriter(outt), true);
//        if (inn == null || outt == null) {
//            System.out.println("Failed to open streams to socket.");
//            return false;
//        }
//        String initialID = in.readLine();
//        System.out.println(initialID);
//        System.out.println("HELO " + localhost.getHostName());
//        out.println("HELO " + localhost.getHostName());
//
//        String welcome = in.readLine();
//        System.out.println(welcome);
//        System.out.println("AUTH LOGIN");
//        out.println("AUTH LOGIN");
//        //base64 from
//        System.out.println("MAIL From:<" + from + ">");
//        out.println("bWF4bGV2NTdAZ21haWwuY29t");
//        System.out.println("pas has been entered");
//        out.println("Ump5eWpoKjE2MDE=");
//        String senderOK = in.readLine();
//        System.out.println(senderOK);
//        out.println("MAIL FROM:<" + from + ">");
//
//        System.out.println("RCPT TO:<" + to + ">");
//        out.println("RCPT TO:<" + to + ">");
//
//        out.println("DATA");
//
////            out.println("MAIL FROM:<" + from + ">");
////            System.out.println("RCPT TO:<" + to + ">");
////            out.println("RCPT TO:<" + to + ">");
//        String recipientOK = in.readLine();
//        System.out.println(recipientOK);
//        out.println("SUBJECT: test\n");
//
//        //String isOk = in.readLine();
//        //System.out.println(isOk);
//        out.println("TEST TEST TEST\nhueta ebanaya\n");
//
//        //String line;
////            while ((line = msg.readLine()) != null) {
////                out.println(line);
////            }
//        System.out.println(".");
//        out.println(".");
//        String acceptedOK = in.readLine();
//        System.out.println(acceptedOK);
//        System.out.println("QUIT");
//        out.println("QUIT");
//        return true;
//    }
//    //}
//}