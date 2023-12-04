import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AdminActions {
    public static void vyber (int volba, Admin admin, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        if (volba==1){
            //print all books
        }
        else if (volba==2){
            AdminActions.addBook(admin, socket, writer);
        }
        else if (volba==3){
            AdminActions.removeBook(admin,socket,writer);
        }
        else if (volba==4) {
            AdminActions.addNewUser(socket,writer, admin);
        }
        else if (volba==5) {
            AdminActions.removeUser(admin,socket,writer);
        }
        else if (volba==6){
            AdminActions.logOUT(admin, socket);
        }else
            System.out.println("Nespravna volba");
    }
    public static void addBook(Admin admin, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadajte nazov knihy na pridanie:");
        String nazov = scan.nextLine();
        System.out.println("Zadajte publishera knihy na pridanie:");
        String publisher = scan.nextLine();
        System.out.println("Zadajte ISBN knihy na pridanie:");
        String ISBN = scan.nextLine();
        System.out.println("Zadajte autora knihy na pridanie:");
        String author = scan.nextLine();
        System.out.println("Zadajte username alebo email usera:");
        String uname=scan.nextLine();
        writer.println("addBA");
        writer.println(nazov);
        writer.println(publisher);
        writer.println(ISBN);
        writer.println(author);
        writer.println(uname);
        System.out.println("Kniha bola pridana");
        InterfaceAdmin.uvodUvod(admin, socket);
    }
    public static void removeBook (Admin admin, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadajte nazov knihy na odstranenie:");
        String nazov = scan.nextLine();
        System.out.println("Zadajte username alebo email usera:");
        String uname = scan.nextLine();
        writer.println("rmvBA"); //TBA removeBookServerside
        writer.println(nazov);
        writer.println(uname);
        System.out.println("Kniha bola vymazana");
        InterfaceAdmin.uvodUvod(admin, socket);
    }
    public static void addNewUser(Socket socket, PrintWriter writer, Admin admin) throws IOException {
       Signup.registerAdmin(socket);
       InterfaceAdmin.uvodUvod(admin, socket);
    }
    public static void removeUser(Admin admin, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadajte nazov usera na odstranenie:");
        String nazov = scan.nextLine();
        writer.println("rmvUA"); //TBA removeBookServerside
        writer.println(nazov);
        System.out.println("User bol vymazany");
        InterfaceAdmin.uvodUvod(admin, socket);
    }
    public static void logOUT (Admin admin, Socket soc) throws IOException {
        admin = null;
        InterfaceUser.uvodUvod(soc);
    }
}
