package Comand;

import App.Bot;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SendMess implements SendMessService{



    @Override
    public void send(String chatId, String mess) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(mess);

        try {
           Bot.bot().execute(sendMessage);
        } catch (TelegramApiException e) {
                 e.printStackTrace();
        }
    }
}
