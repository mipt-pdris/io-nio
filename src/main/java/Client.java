import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {

        try (Socket socket = new Socket("localhost", 8080);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             final Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String msg = scanner.nextLine();
                writer.println(msg);
                System.out.println("Send to server: " + msg);
                System.out.println("Server replied with: " + reader.readLine());
            }
        }
    }
}
