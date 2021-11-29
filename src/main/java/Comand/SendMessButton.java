package Comand;

import App.Bot;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SendMessButton implements SendMessService{



    @Override
    public void send(String chatId, String mess) {
        String name=mess.trim();

        String [] names=name.split("/");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        String message="Тут может быть ваша реклама!";
        sendMessage.setText(message);

        try {
            setButtons(sendMessage, names);
            Bot.bot().execute(sendMessage);
        } catch (TelegramApiException e) {
                 e.printStackTrace();
        }
    }

    public synchronized void setButtons(SendMessage sendMessage, String [] names) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        // replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();


            KeyboardRow keyboardRow;
            for (String name:names){
                keyboardRow = new KeyboardRow();
                keyboardRow.add(new KeyboardButton(name));
                keyboard.add(keyboardRow);
            }




        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


}
