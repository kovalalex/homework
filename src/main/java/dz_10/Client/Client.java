package dz_10.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Класс client
 */
public class Client {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket();
        Reciever reciever = new Reciever(socket);
        reciever.start();
        boolean stop = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!stop) {

            try {
                String msg = reader.readLine();
                DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getLocalHost(), 7777); //Засылаем на порт сервера который забинден
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
