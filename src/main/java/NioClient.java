import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class NioClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 1000; i++) {
            final SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8081));
            channel.write(ByteBuffer.wrap(("Hello from client " + i).getBytes(UTF_8)));
            Thread.sleep(5000L);
        }
    }
}
