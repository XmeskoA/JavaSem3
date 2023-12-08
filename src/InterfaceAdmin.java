import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.Socket;

/**
 * Class provides introduction menus for admin exclusively
 */
public class InterfaceAdmin {
    /**
     * Shows the introduction menu for the admin
     * @param odpoved Response message
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @throws IOException
     */

    public static void uvod (String odpoved, Admin admin, Socket socket) throws IOException {
        Scanner scan= new Scanner(System.in);
        System.out.println("Welcome in the Library admin "+ odpoved);
        System.out.println("1- Show all the book in the library");
        System.out.println("2 - Add book for specific user");
        System.out.println("3 - Delete book from specific user");
        System.out.println("4- Add new user");
        System.out.println("5- Delete user");
        System.out.println("6- log out");
        int volba1= Integer.parseInt(scan.nextLine());
        AdminActions.vyber(volba1, admin, socket);

    }

    /**
     * Shows the introduction menu for the admin
     * Sends inputted choice to the class that provides methods for performing various admin actions
     * @param admin Admin representing current admin
     * @param socket Socket communication
     * @throws IOException
     */
    public static void uvodUvod (Admin admin, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1- Show all the book in the library");
        System.out.println("2 - Add book for specific user");
        System.out.println("3 - Delete book from specific user");
        System.out.println("4- Add new user");
        System.out.println("5- Delete user");
        System.out.println("6- log out");
        int volba1 = Integer.parseInt(scan.nextLine());
        AdminActions.vyber(volba1, admin, socket);
    }
}