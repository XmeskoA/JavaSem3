import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.net.Socket;

public class UserActions {

    public static void vyber (int volba, User user, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if (volba==1){
            UserActions.zobrazBook(user, socket , writer, reader);
        }
        else if (volba==2){
            UserActions.addBook(user, socket, writer);
        }
        else if (volba==3){
            UserActions.removeBook(user,socket,writer);
        }
        else if (volba==4){
            UserActions.logOUT(user, socket);
        }else
            System.out.println("Nespravna volba");
    }
    public static void addBook (User user, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadajte nazov knihy na pridanie:");
        String nazov = scan.nextLine();
        System.out.println("Zadajte publishera knihy na pridanie:");
        String publisher = scan.nextLine();
        System.out.println("Zadajte ISBN knihy na pridanie:");
        String ISBN = scan.nextLine();
        System.out.println("Zadajte autora knihy na pridanie:");
        String author = scan.nextLine();
        writer.println("addB");
        writer.println(nazov);
        writer.println(publisher);
        writer.println(ISBN);
        writer.println(author);
        writer.println(user.getID());
        System.out.println("Kniha bola pridana");
        InterfaceUser.uvodPoVybere(socket, user);
    }

    public static void removeBook (User user, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadajte nazov knihy na odstranenie:");
        String nazov = scan.nextLine();
        writer.println("rmvB"); //TBA removeBookServerside
        writer.println(nazov);
        writer.println(user.getID());
        System.out.println("Kniha bola vymazana");
        InterfaceUser.uvodPoVybere(socket, user);
    }
    public static void zobrazBook (User user, Socket socket, PrintWriter writer, BufferedReader reader) throws IOException {
        writer.println("showB");
        String [] kniha= new String[6];
        ArrayList<Book> books= new ArrayList<>();
        int pocetKnih=0, infoOKnihach=0;
        int pocetRiadkov= Integer.parseInt(reader.readLine());
        while (pocetKnih<pocetRiadkov){
            for (infoOKnihach=0; infoOKnihach<6;infoOKnihach++){
                kniha[infoOKnihach]=reader.readLine();
            }
            books.add(new Book(kniha[1],kniha[2], kniha[3], kniha[4], Integer.parseInt(kniha[5])));
            System.out.println(books);
        }
        for (Book i : books){
            System.out.println(i);
        }
        InterfaceUser.uvodPoVybere(socket, user);

    }
    public static void logOUT (User user, Socket soc) throws IOException {
        user= null;
        InterfaceUser.uvodUvod(soc);
    }
}
