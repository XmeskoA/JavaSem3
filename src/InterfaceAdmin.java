import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.Socket;
public class InterfaceAdmin {
    public static void uvod (String odpoved, Admin admin, Socket socket) throws IOException {
        Scanner scan= new Scanner(System.in);
        System.out.println("Vitaj v kniznici admin"+ odpoved);
        System.out.println("1- zobrazi vsetky knihy v kniznici");
        System.out.println("2 - Moznost pridat knihu do kniznice konkretneho usera");
        System.out.println("3 - vymazanie knihy konkretnemu userovi");
        System.out.println("4- pridanie noveho usera");
        System.out.println("5- vymazanie usera");
        System.out.println("6- log out");
        int volba1= Integer.parseInt(scan.nextLine());
        AdminActions.vyber(volba1, admin, socket);

    }
    public static void uvodUvod (Admin admin, Socket socket) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("1- zobrazi vsetky knihy v kniznici");
        System.out.println("2 - Moznost pridat knihu do kniznice konkretneho usera");
        System.out.println("3 - vymazanie knihy konkretnemu userovi");
        System.out.println("4- pridanie noveho usera");
        System.out.println("5- vymazanie usera");
        System.out.println("6- log out");
        int volba1 = Integer.parseInt(scan.nextLine());
        AdminActions.vyber(volba1, admin, socket);
    }
}
