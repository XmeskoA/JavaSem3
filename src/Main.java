import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketOption;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
 public static void main (String[] args) throws IOException {
  Scanner scan= new Scanner(System.in);
 Socket soc = new Socket ("localhost", 6969);


     InterfaceUser.uvodUvod(soc);

 }
}