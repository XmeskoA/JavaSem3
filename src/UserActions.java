import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.net.Socket;

/**
 * Class provides methods for performing various user actions
 */
public class UserActions {
    /**
     * Executes specified actions based on the provided choice
     * @param volba Input of the user
     * @param user User representing current user
     * @param socket Socket communication
     * @throws IOException
     */
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
            System.out.println("wrong choice");
    }

    /**
     * Adds book based on the specified input
     * @param user User representing current user
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @throws IOException
     */
    public static void addBook (User user, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the book's title:");
        String nazov = scan.nextLine();
        System.out.println("Please enter the publisher of the book:");
        String publisher = scan.nextLine();
        System.out.println("Please enter the isbn of the book:");
        String ISBN = scan.nextLine();
        System.out.println("Please enter author of the book:");
        String author = scan.nextLine();
        writer.println("addB");
        writer.println(nazov);
        writer.println(publisher);
        writer.println(ISBN);
        writer.println(author);
        writer.println(user.getID());
        System.out.println("Book was added");
        InterfaceUser.uvodPoVybere(socket, user);
    }

    /**
     * Removes book based on the specified input
     * @param user User representing current user
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @throws IOException
     */
    public static void removeBook (User user, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter name of the book:");
        String nazov = scan.nextLine();
        writer.println("rmvB");
        writer.println(nazov);
        writer.println(user.getID());
        System.out.println("Book was erased");
        InterfaceUser.uvodPoVybere(socket, user);
    }

    /**
     * Shows the list of books from the library
     * @param user User representing current user
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @param reader Receiving data from the server
     * @throws IOException
     */
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
            pocetKnih++;
            //System.out.println(books);
        }
        System.out.println("These are all the books in library");
        for (Book i : books){
            writer.println("gimme");
            writer.println(i.getOwnerID());
            String username= reader.readLine();
            System.out.println(i.getTitle() +"   "+i.getAuthor() +"   " +i.getPublisher()+ "   " +username );
        }
        InterfaceUser.uvodPoVybere(socket, user);

    }

    /**
     * Logs out the user
     * Returns to default state - user's interface
     * @param user User representing current user
     * @param soc Socket communication
     * @throws IOException
     */
    public static void logOUT (User user, Socket soc) throws IOException {
        user= null;
        InterfaceUser.uvodUvod(soc);
    }
}