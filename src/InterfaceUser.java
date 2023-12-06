import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.Socket;

public class InterfaceUser {
    public static void Uvod (String odpoved, User user, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Vitaj v kniznici"+ odpoved);
        System.out.println("1- zobrazi vsetky knihy v kniznici");
        System.out.println("2 - Moznost pridat knihu do personalnej kniznice");
        System.out.println("3- Vymazanie knihy z personalnej kniznice");
        System.out.println("4- odhlasenie z kniznice");
        int volba1= Integer.parseInt(scan.nextLine());
        UserActions.vyber(volba1,user, socket);

    }
    public static void uvodPoVybere (Socket socket, User user) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1- zobrazi vsetky knihy v kniznici");
        System.out.println("2 - Moznost pridat knihu do personalnej kniznice");
        System.out.println("3- Vymazanie knihy z personalnej kniznice");
        System.out.println("4- odhlasenie z kniznice");
        int volba1= Integer.parseInt(scan.nextLine());
        UserActions.vyber(volba1,user, socket);
    }
    public static void uvodUvod (Socket soc) throws IOException {
        Scanner scan= new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        System.out.println("Login or signup?");
        System.out.println("Zadajte svoju volbu:");
        String volba= scan.nextLine();
        if (volba.equals("signup")) {
            System.out.println("si v signupe");
            Signup signIn = new Signup();
            signIn.register(soc, reader);
            String[] odpoved = new String[3];
            for (int i = 0; i < 3; i++) {
                odpoved[i] = reader.readLine();
                System.out.println("som vo for cykle");
                System.out.println(odpoved[i]);
                if (odpoved[0] == "null") {
                    System.out.println("User uz existuje");
                    InterfaceUser.uvodUvod(soc);
                }
            }
            System.out.println("som pri logine");
            Login.loginFromSignUp(soc, odpoved[1], odpoved[2]);
        }

        else if (volba.equals("login")){
            String[] odpoved = new String[5];
            for (int i=0; i<5; i++){
                odpoved[i]= reader.readLine();
            }
            Login.loginUser(odpoved, soc);
        }
    }
}
