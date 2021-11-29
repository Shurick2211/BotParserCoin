package Comand;

import App.UserBox;
import org.telegram.telegrambots.api.objects.Message;

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
        if(!UserBox.getUser(message.getChatId().toString()).equals(null))
            mess="Привет! Вы наш клиент!";
        sendMessService.send(message.getChatId().toString(),mess); }
}
