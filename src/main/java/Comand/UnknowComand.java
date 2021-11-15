package Comand;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class UnknowComand implements Comand{
    private final SendMessService sendMessService;
    private final String mess="О чем ты! \n Помощь: /help";

    public UnknowComand(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        sendMessService.send(message.getChatId().toString(),mess);
    }
}
