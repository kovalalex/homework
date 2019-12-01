package dz_10.Data;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

/**
 * Класс содержащий данные о клиенте
 */
public class Connection {
    @Getter
    @Setter
    private InetAddress addr;
    @Getter
    @Setter
    private int port;
    @Getter
    @Setter
    private DatagramSocket clientSocket;
    @Getter
    @Setter
    private String username;

    public Connection(InetAddress addr, int port, DatagramSocket clientSocket, String username) {
        this.addr = addr;
        this.port = port;
        this.clientSocket = clientSocket;
        this.username = username;
    }

    public void send(String msg) {
        DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, port);
        try {
            clientSocket.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return getPort() == that.getPort() &&
                Objects.equals(getAddr(), that.getAddr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddr(), getPort());
    }

    @Override
    public String toString() {
        return "Connection{" +
                "addr=" + addr +
                ", port=" + port +
                ", clientSocket=" + clientSocket +
                ", username='" + username + '\'' +
                '}';
    }
}
