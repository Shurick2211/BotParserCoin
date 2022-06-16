package Comand;

import App.Bot;
import App.DataBase;
import App.UserBox;

import org.telegram.telegrambots.meta.api.objects.Message;

public class StopComand implements Comand{
    private final SendMessService sendMessService;
    private final String mess="Вы отписались от рассылки!";

    public StopComand(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        Bot.logger.info("Comand STOP: "+message.getChatId().toString());
        sendMessService.send(message.getChatId().toString(),mess);
        UserBox.deleteUser(UserBox.getUser(message.getChatId().toString()), new DataBase());
        UserBox.users.remove(UserBox.getUser(message.getChatId().toString()));
        ComandBox comandBox = new ComandBox(new SendMessButton());
        comandBox.useComand("BUTTON").execute(message);

    }
}
