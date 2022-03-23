package main;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import controllers.SocketController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    // Ну, здесь изи
    public static void main(String[] args) throws IOException, MessagingException, javax.mail.MessagingException {
        // Создаём сервак
        ServerSocket server = new ServerSocket(6967);
        System.out.println("Ты подключился, братан!");
        // Создаём клиентов
        ArrayList<Socket> clients = new ArrayList<Socket>();
        while (true) {
            // Подсоединяем их на сервак
            Socket new_client = server.accept();
            clients.add(new_client);
            for (Socket client : clients) {
                // Запускаем сессию
                SocketController thread = new SocketController(client);
                thread.start();
            }
        }
    }
    // Готово
}
