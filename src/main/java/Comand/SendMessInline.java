package Comand;

import App.Bot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SendMessInline implements SendMessService{



    @Override
    public void send(String chatId, String mess) {
        String name=mess.trim().split("=")[0];
        String text=mess.trim().split("=")[1];
        String [] names=name.split("/");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.enableHtml(true);
        sendMessage.setText(text);

        try {
            setInButon(sendMessage, names);
            Bot.bot().execute(sendMessage);
        } catch (TelegramApiException e) {
                 e.printStackTrace();
        }
    }

    private void setInButon(SendMessage sendMessage,String[] names) {

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        int dat;
        if (names.length>2) dat=1;
        else dat=101;
        for (String name:names){
            if(dat%2!=0)
                 buttonsRow = new ArrayList<>();
            InlineKeyboardButton inButton =new InlineKeyboardButton();
            inButton.setText(name);
            inButton.setCallbackData(dat+"");
            buttonsRow.add(inButton);
            if(dat%2!=1)
                buttons.add(buttonsRow);
            dat++;
        }

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        inlineKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(inlineKeyboard);
    }


}
