import java.io.*;
import java.util.Scanner;
import java.net.Socket;

/**
 * Class provides introduction menus for user exclusively
 */
public class InterfaceUser {
    /**
     * Shows the introduction menu for the newly created user
     * @param odpoved Response message
     * @param user User representing current user
     * @param socket Socket communication
     * @throws IOException
     */
    public static void Uvod (String odpoved, User user, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the library "+ odpoved);
        System.out.println("1- Show all book in the library");
        System.out.println("2 - Add book to your collection");
        System.out.println("3- Delete a book from your collection");
        System.out.println("4- Log out");
        int volba1= Integer.parseInt(scan.nextLine());
        UserActions.vyber(volba1,user, socket);

    }

    /**
     * Shows the introduction menu for the user
     * Processes user's input
     * @param socket Socket communication
     * @param user User representing current user
     * @throws IOException
     */
    public static void uvodPoVybere (Socket socket, User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1- Show all book in the library");
        System.out.println("2 - Add book to your collection");
        System.out.println("3- Delete a book from your collection");
        System.out.println("4- Log out");
        int volba1= Integer.parseInt(scan.nextLine());
        UserActions.vyber(volba1,user, socket);
    }

    /**
     * Shows the signup or login selection and processes the user's input
     * @param soc Socket communication
     * @throws IOException
     */
    public static void uvodUvod (Socket soc) throws IOException {
        Scanner scan= new Scanner(System.in);
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(soc.getOutputStream(), "UTF-8"), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        System.out.println("login or signup?");
        System.out.println("Please type in your choice:");
        String volba= scan.nextLine();
        if (volba.equals("signup")) {
            Signup signIn = new Signup();
            signIn.register(soc, reader);
            String[] odpoved = new String[3];
            for (int i = 0; i < 3; i++) {
                odpoved[i] = reader.readLine();
                if (odpoved[0] == "null") {
                    System.out.println("User already exist");
                    InterfaceUser.uvodUvod(soc);
                }
            }
            Login.loginFromSignUp(soc, odpoved[0], odpoved[1]);
        }

        else if (volba.equals("login")){
            Login.loginGUI(soc);
            String[] odpoved = new String[5];
            for (int i=0; i<5; i++){
                odpoved[i]= reader.readLine();
            }
            Login.loginUser(odpoved, soc);
        }
        else if (volba.equals("exit")){
            writer.println("exit");
            soc.close();
        }
        else {
            System.out.println("You chose non-existent option");
            System.out.println("Try again");
            InterfaceUser.uvodUvod(soc);
        }
    }
}