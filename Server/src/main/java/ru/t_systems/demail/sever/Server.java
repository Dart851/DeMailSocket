package ru.t_systems.demail.sever;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.CommandType;

public class Server {

    ServerSocket serverSocket;
    boolean ServerOn = true;

    public Server() {

        try {
            serverSocket = new ServerSocket(11111);
        } catch (IOException ioe) {
            System.out
                    .println("Not create server socket. WTF?.");
            System.exit(-1);
        }

        while (ServerOn) {
            try {

                Socket clientSocket = serverSocket.accept();

                ClientThread cliThread = new ClientThread(
                        clientSocket, this);
                cliThread.start();

            } catch (IOException ioe) {
                System.out
                        .println("Socket is fall :");
                ioe.printStackTrace();
            }

        }

        try {
            serverSocket.close();
            System.out.println("Server Stopped.");
        } catch (Exception ioe) {
            System.out.println("Problem stopping server.");
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        new Server();

    }
}