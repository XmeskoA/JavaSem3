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
            signIn.register(soc);
            String[] odpoved = new String[5];
            for (int i = 0; i == 4; i++) {
                odpoved[i] = reader.readLine();
                if (odpoved[0] == "true") {
                    System.out.println("User uz existuje");
                    InterfaceUser.uvodUvod(soc);
                }
            }
            Login.loginUser(odpoved,soc);
        }

        else if (volba.equals("login")){
            String[] odpoved = new String[5];
            for (int i=0; i == 4; i++){
                odpoved[i]= reader.readLine();
            }
            Login.loginUser(odpoved, soc);
        }
    }
}
