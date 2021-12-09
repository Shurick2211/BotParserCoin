package Comand;

import App.Bot;
import App.UserBox;

import org.telegram.telegrambots.meta.api.objects.Message;

public class StartComand implements Comand{
    private final SendMessService sendMessService;
    private String mess="Вас приветствует бот следящий за курсом ...коинов!\n" +
            "Вам надо зарегистрироваться, выбрать валюту, указать пределы min и MAX," +
            " указать за сколько $ вы купили свои ...коины (если не купили, то любое число)\n" +
            "Бот будет вас информировать о достижении min и MAX, а также об изменениях курса " +
            "между задаными границами" +
            "\nЗарегистрироваться можно кнопкой внизу или командой /reg";

    public StartComand(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        Bot.logger.info("Comand Start: "+message.getChatId().toString());
        if(UserBox.getUser(message.getChatId().toString())!=null)
            mess="Привет! Вы наш клиент!";
        sendMessService.send(message.getChatId().toString(),mess);
        ComandBox comandBox = new ComandBox(new SendMessButton());
        comandBox.useComand("BUTTON").execute(message);
    }
}
