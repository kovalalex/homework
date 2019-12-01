package dz_10.Server;

import dz_10.Data.Connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Основной класс сервера
 */
public class Server {

    public static void main(String[] args) {
        /**
         * map для хранения подключений.
         */
        HashMap<Integer, Connection> map = new HashMap<>();

        try (DatagramSocket serverSocket = new DatagramSocket(7777)) {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket dgpacket = new DatagramPacket(buffer, buffer.length);

                try {
                    serverSocket.receive(dgpacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Connection connection;
                if ((connection = map.get(Objects.hash(dgpacket.getAddress(), dgpacket.getPort()))) != null) {
                    String msg = connection.getUsername() + ": " + new String(dgpacket.getData(), 0, dgpacket.getData().length).trim();
                    for (Map.Entry<Integer, Connection> entry : map.entrySet()) {
                        entry.getValue().send(msg);
                    }
                    continue;
                }
                System.out.println("receive!");
                String username = new String(dgpacket.getData(), 0, dgpacket.getData().length).trim();
                String msg = username + " has been connected";
                System.out.println(msg);

                connection = new Connection(dgpacket.getAddress(), dgpacket.getPort(), new DatagramSocket(), username);
                System.out.println(connection);
                map.put(connection.hashCode(), connection);
                for (Map.Entry<Integer, Connection> entry : map.entrySet()) {
                    entry.getValue().send(msg);
                }


            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}

