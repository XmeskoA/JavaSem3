import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketOption;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
 public static void main (String[] args) throws IOException {
  Scanner scan= new Scanner(System.in);
 Socket soc = new Socket ("localhost", 6969);


     InterfaceUser.uvodUvod(soc);

 }
}