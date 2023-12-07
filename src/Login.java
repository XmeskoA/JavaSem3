import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Login {
    public static void loginUser ( String[] odpoved, Socket socket) throws IOException {
        System.out.println("prisiel som az k loginu");

        if (Integer.parseInt(odpoved[4])== 0){
            User user = new User (Integer.parseInt(odpoved[3]),odpoved[0], odpoved[1], odpoved[2],0);
            System.out.println("vytvoril som noveho usera");
            InterfaceUser.Uvod(odpoved[0], user, socket );
        }
        else if (Integer.parseInt(odpoved[4])== 1){
            Admin admin = new Admin (Integer.parseInt(odpoved[3]),odpoved[0], odpoved[1], odpoved[2],1);
            System.out.println("Vitaj v kniznici"+ odpoved[0]);
            System.out.println("Zobraz- zobrazi vsetky knihy v kniznici");
            System.out.println("Pridat - Moznost pridat knihu do kniznice konkretneho usera");
            System.out.println("Vymaz - vymazanie knihy konkretnemu userovi");
        }

    }
    public static void loginGUI (Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zadajte svoje username alebo Email:");
        String uname = scan.nextLine();
        System.out.println("Zadajte svoje heslo:");
        String upassw= scan.nextLine();
        PrintWriter writer= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        writer.println("userL");
        writer.println(uname);
        writer.println(upassw);
    }
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
           System.out.println("dataNaLogin"+ dataNaLogin[i]);
       }
       if (dataNaLogin[0].equals("nula")){
           System.out.println("user neexistuje");
           Login.loginGUI(socket);
       }
       else {
        System.out.println("som v mojej novej funkcii");
        Login.loginUser(dataNaLogin, socket);
       }
    }

}
