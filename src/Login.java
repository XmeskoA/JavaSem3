import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Login {
    public static void loginUser ( BufferedReader reader) throws IOException {
        String[] odpoved = new String[5];
        for (int i=0; i == 4; i++){
            odpoved[i]= reader.readLine();
            if (odpoved[0]=="true"){
                System.out.println("User uz existuje");
                break;
            }
        }
        if (Integer.parseInt(odpoved[4])== 0){
            User user = new User (Integer.parseInt(odpoved[4]),odpoved[0], odpoved[1], odpoved[2],0);
            InterfaceUser.Uvod(odpoved[0], user);
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
        writer.println(uname);
        writer.println(upassw);
    }

}
