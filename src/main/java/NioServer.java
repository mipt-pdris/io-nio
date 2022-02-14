import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8081));
        serverSocketChannel.configureBlocking(false);
        final Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            final Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                final SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    register(selector, serverSocketChannel);
                }
                if (key.isReadable()) {
                    readMsg(key);
                }
                keyIterator.remove();
            }
        }
    }

    private static void readMsg(SelectionKey key) {
        final SocketChannel channel = (SocketChannel) key.channel();
        final ByteBuffer allocate = ByteBuffer.allocate(10);
        try {
            channel.read(allocate);
            allocate.flip();
            byte[] msg = new byte[allocate.remaining()];
            allocate.get(msg, 0, msg.length);
            System.out.println(new String(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocketChannel) {
        try {
            final SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.println("New client is connected");
        } catch (IOException e) {

        }
    }
}
