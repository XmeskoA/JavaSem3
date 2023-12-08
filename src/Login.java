import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class provides various logins
 */
public class Login {
    /**
     * Authenticates the user based on the server's response and redirects to the appropriate interface
     * @param odpoved Response message
     * @param socket Socket communication
     * @throws IOException
     */
    public static void loginUser ( String[] odpoved, Socket socket) throws IOException {
        System.out.println("prisiel som az k loginu");

        if (Integer.parseInt(odpoved[4])== 0){
            User user = new User (Integer.parseInt(odpoved[3]),odpoved[0], odpoved[1], odpoved[2],0);
            System.out.println("vytvoril som noveho usera");
            InterfaceUser.Uvod(odpoved[0], user, socket );
        }
        else if (Integer.parseInt(odpoved[4])== 1){
            Admin admin = new Admin (Integer.parseInt(odpoved[3]),odpoved[0], odpoved[1], odpoved[2],1);
           InterfaceAdmin.uvod(odpoved[0], admin, socket);

        }

    }

    /**
     * Shows and prompts the user to input their credentials
     * Input gets sent to the server
     * @param socket Socket communication
     * @throws IOException
     */
    public static void loginGUI (Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your username or email:");
        String uname = scan.nextLine();
        System.out.println("Enter your password:");
        String upassw= scan.nextLine();
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        writer.println("userL");
        writer.println(uname);
        writer.println(upassw);
    }

    /**
     * Performs login based on data received from signup and redirects to the appropriate interface
     * @param socket Socket communication
     * @param uname Obtained username
     * @param upassw Obtained password
     * @throws IOException
     */
    public static void loginFromSignUp (Socket socket, String uname, String upassw) throws IOException {
        //int i=0;
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.println("userL");
        writer.println(uname);
        writer.println(upassw);
        String [] dataNaLogin= new String[5];
       for (int i=0;i<5;i++){
           dataNaLogin[i]= reader.readLine();
           //System.out.println("dataNaLogin"+ dataNaLogin[i]);
       }
       if (dataNaLogin[0].equals("nula")){
           System.out.println("user doesn't exist");
           Login.loginGUI(socket);
       }
       else {
        Login.loginUser(dataNaLogin, socket);
       }
    }

}