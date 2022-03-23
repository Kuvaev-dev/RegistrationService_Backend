package controllers;

import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import models.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketController extends Thread {
    // Значит так
    // В параметрах конструктора инициализируем сокет клиента
    public SocketController(Socket client) throws IOException, MessagingException, javax.mail.MessagingException {
        // Получаем адрес текущего клиента
        String address = (client.getInetAddress()).toString().replace("/", " ");
        System.out.println("Клиент " + address + " успешно присоединён!");
        // Запускаем процесс регистрации
        DataOutputStream output = new DataOutputStream(client.getOutputStream());
        output.writeUTF("Welcome" + address + "! You registration request in processing!");
        output.flush();
        // Создаём буффер и считываем в него данные
        byte[] buffer = new byte[10000];
        DataInputStream inputStream = new DataInputStream(client.getInputStream());
        inputStream.read(buffer);
        // Получем юзера через гугл апи и отправляем письмо об успешной регистрации
        Gson gson = new Gson();
        User user = gson.fromJson(new String(buffer,"UTF8").replace("\u0000",""), User.class);
        MailController.SendMail(user);
    }
    // Конец
}
