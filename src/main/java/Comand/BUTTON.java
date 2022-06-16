package Comand;

import App.UserBox;
import org.telegram.telegrambots.meta.api.objects.Message;

public class BUTTON implements Comand{
    private final SendMessService sendMessService;
    private String mess="";

    public BUTTON(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        if (UserBox.getUser(message.getChatId().toString())==null){
            mess="Регистрация";
        }else {
            mess="Изменить валюту/Изменить min/Изменить MAX/STOP";
            if(message.getChatId().toString().equals("1005227074"))
                mess+="/качать users";
        }

        sendMessService.send(message.getChatId().toString(),mess);
    }
}
