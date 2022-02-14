import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                executorService.execute(() -> {
                    try {
                        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        System.out.println(bufferedReader.readLine());
                    } catch (IOException e) {

                    }
                });
            }
        }
    }
}
