import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class provides methods for performing various admin actions
 */
public class AdminActions {
    /**
     * Executes specified actions based on the provided choice
     * @param volba Input of the admin
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @throws IOException
     */
    public static void vyber (int volba, Admin admin, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if (volba==1){
            AdminActions.zobrazABook(admin, socket, writer, reader);
        }
        else if (volba==2){
            AdminActions.addBookToUser(admin, socket, writer);
        }
        else if (volba==3){
            AdminActions.removeBookFromUser(admin,socket,writer);
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

    /**
     * Input and add a book to a specified user's library
     * Inputted data gets sent to the server
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @throws IOException
     */
    public static void addBookToUser(Admin admin, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadaj meno pouzivatela, ktoremu mam pridat knihu: ");
        String username = scan.nextLine();
        System.out.println("Zadaj specifikacie knihy: ");
        System.out.print("Title: ");
        String title = scan.nextLine();
        System.out.print("Publisher: ");
        String publisher = scan.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scan.nextLine();
        System.out.print("Author: ");
        String author = scan.nextLine();
        writer.println("addBtoU");
        writer.println(username);
        writer.println(title);
        writer.println(publisher);
        writer.println(ISBN);
        writer.println(author);
        System.out.println("Kniha bola pridana do kniznice uzivatela " + username + " !");
        InterfaceAdmin.uvodUvod(admin, socket);
    }

    /**
     * Input and remove a book from a specified user's library
     * Inputted data gets sent to the server
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @throws IOException
     */
    public static void removeBookFromUser (Admin admin, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter username or email from whom the book will be deleted: ");
        String username = scan.nextLine();
        System.out.println("Enter the book's title: ");
        String title = scan.nextLine();
        writer.println("rmvBfromU");
        writer.println(username);
        writer.println(title);
        System.out.println("Kniha " + title + " was erased from library of " + username + " !");
        InterfaceAdmin.uvodUvod(admin, socket);
    }

    /**
     * Admin adds new user from a specified input
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @param admin Admin representing current admin
     * @throws IOException
     */
    public static void addNewUser(Socket socket, PrintWriter writer, Admin admin) throws IOException {
       Signup.registerUserByAdmin(socket);
       InterfaceAdmin.uvodUvod(admin, socket);
    }

    /**
     * Admin removes existing user from a specified input
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @throws IOException
     */
    public static void removeUser(Admin admin, Socket socket, PrintWriter writer) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name or username of a user to be deleted:");
        String email = scan.nextLine();
        writer.println("rmvUA");
        writer.println(email);
        System.out.println("User was deleted");
        InterfaceAdmin.uvodUvod(admin, socket);
    }

    /**
     * Shows the list of books from the library
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @param writer Sending data to the server
     * @param reader Receiving data from the server
     * @throws IOException
     */
    public static void zobrazABook(Admin admin, Socket socket, PrintWriter writer, BufferedReader reader) throws IOException {
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
        System.out.println("These are all the book is library");
        //writer.println("gimme");
        for (Book i : books){
            writer.println("gimme");
            int idecko= i.getOwnerID();
            writer.println(idecko);
            System.out.println(idecko);
            String username= reader.readLine();
            System.out.println(i.getTitle() +"   "+i.getAuthor() +"   " +i.getPublisher()+ "   " +username );
        }
        InterfaceAdmin.uvodUvod(admin, socket);
    }

    /**
     * Logs out the admin
     * Returns to default state - user's interface
     * @param admin Admin representing current admin
     * @param soc Socket communication
     * @throws IOException
     */
    public static void logOUT (Admin admin, Socket soc) throws IOException {
        admin = null;
        InterfaceUser.uvodUvod(soc);
    }
}