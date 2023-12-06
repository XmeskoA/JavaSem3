import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
public class Signup {
    public void register (Socket socket, BufferedReader reader) throws IOException {
        Scanner scan= new Scanner(System.in);
        System.out.println("Zadajte svoj username:");
        String uname= scan.nextLine();
        System.out.println("Zadajte svoj email:");
        String uemail= scan.nextLine();
        System.out.println("Zadajte svoje heslo:");
        String upassword= scan.nextLine();
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        writer.println("addU");
        writer.println(uname);
        writer.println(uemail);
        writer.println(upassword);
        reader.readLine();


    }
    public static void registerUserByAdmin(Socket socket) throws IOException {
        Scanner scan= new Scanner(System.in);
        System.out.println("Zadajte pouzivatelov username:");
        String uname= scan.nextLine();
        System.out.println("Zadajte pouzivatelov email:");
        String uemail= scan.nextLine();
        System.out.println("Zadajte pouzivatelove heslo:");
        String upassword= scan.nextLine();
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        writer.println("addUA");
        writer.println(uname);
        writer.println(uemail);
        writer.println(upassword);

    }
}
