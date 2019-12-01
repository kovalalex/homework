package dz_10.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Поток - получающий сообщения от сервера
 */
public class Reciever extends Thread {
    DatagramSocket socket;

    public Reciever(DatagramSocket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        try {

            boolean running = true;
            //  byte[]buf = new byte[256];

            while (running) {
                byte[] buf = new byte[256];
                DatagramPacket packet
                        = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received
                        = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
                if (received.equals("end")) {
                    running = false;
                    continue;
                }

            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
