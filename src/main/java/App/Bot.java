package App;

import Comand.ComandBox;
import Comand.SendMess;
import Comand.SendMessButton;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {

    private static String BOTTOKEN;
    private static String BOTNAME;
    private static Bot bot;


    ComandBox comandBox;

    public static Bot bot() {
        if (bot==null) {
            botConfig();
           ApiContextInitializer.init();
           TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
           bot = new Bot();
           try {
               telegramBotsApi.registerBot(bot);

           } catch (TelegramApiRequestException e) {
               e.printStackTrace();
           }
       }
        return bot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        int doReg;

    if(update.hasMessage()) {
        if (UserBox.getUser(update.getMessage().getChatId().toString()) == null) doReg = 0;
        else doReg = UserBox.getUser(update.getMessage().getChatId().toString()).doRegistration;
        String message = update.getMessage().getText().trim();
        comandBox = new ComandBox(new SendMess());
        if (message.startsWith("/")) {
            String idinteficator = message.split(" ")[0].toLowerCase();
            System.out.println(idinteficator);
            comandBox.useComand(idinteficator).execute(update.getMessage());
        } else

            if (doReg > 0) {
                comandBox.useComand("/reg").execute(update.getMessage());
            } else

            {
                comandBox.useComand("NO").execute(update.getMessage());
                comandBox = new ComandBox(new SendMessButton());
                comandBox.useComand("BUTTON").execute(update.getMessage());
            }

    }else
    if(update.hasCallbackQuery()){
        int i=Integer.parseInt(update.getCallbackQuery().getData());
        switch (i) {
            case 101: {
                UserBox.getUser(update.getCallbackQuery().getMessage().getChatId().toString()).setInfo(true);
                break;
            }
            case 102: {
                UserBox.getUser(update.getCallbackQuery().getMessage().getChatId().toString()).setInfo(false);
                break;
            }
            default: {//выбор валюты
                i = i - 1;
                UserBox.getUser(update.getCallbackQuery().getMessage().getChatId().toString()).
                        setValuta(ParseKurce.valutas.get(i).getName());
                new SendMess().send(update.getCallbackQuery().getMessage().getChatId().toString(),
                        ParseKurce.valutas.get(i).toString());
            }
        }
        //продолжение регистрации
        comandBox=new ComandBox(new SendMess());
        comandBox.useComand("/reg").execute(update.getCallbackQuery().getMessage());

    }
    }


    @Override
    public String getBotUsername() {
        return BOTNAME;
    }

    @Override
    public String getBotToken() {
        return BOTTOKEN;
    }


    private static void botConfig(){
        final String rootPath ="resources/bot.properties";
        Properties botProps = new Properties();
        try {
            botProps.load(new FileInputStream(rootPath));
            BOTNAME=botProps.getProperty("BOTNAME");
            BOTTOKEN=botProps.getProperty("BOTTOKEN");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
