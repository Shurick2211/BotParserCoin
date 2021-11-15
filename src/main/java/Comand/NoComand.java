package Comand;

import App.Bot;
import App.ParseKurce;
import App.UserBox;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;

public class NoComand implements Comand{
    private final SendMessService sendMessService;
    private static String mess="!";

    public NoComand(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        switch (message.getText()){
            case "Регистрация" :
            case  "Изменить валюту" : {
                ComandBox comandBox=new ComandBox(sendMessService);
                comandBox.useComand("/reg").execute(message);
                break;
            }
            case "Изменить min" :{
                UserBox.getUser(message.getChatId().toString()).doRegistration=22;
                mess="Введите min";
                UserBox.getUser(message.getChatId().toString()).setMin(0.0);
                break;
            }
            case "Изменить MAX" :{
                UserBox.getUser(message.getChatId().toString()).doRegistration=33;
                mess="Введите MAX";
                UserBox.getUser(message.getChatId().toString()).setMax(0.0);
                break;
            }
            case "качать users":{
                try {
                    Bot.bot().sendDocument(new SendDocument().setChatId("1005227074").setNewDocument(new File("resources/users.dat")));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            }
            default : {
                if(UserBox.getUser(message.getChatId().toString())!=null)
                    mess="Курс Вашей валюты: "+ ParseKurce.getValuta(
                            UserBox.getUser(message.getChatId().toString()).getValuta())+"\n"
                            +"Ваш min: "+UserBox.getUser(message.getChatId().toString()).getMin()+"$"+"\n"
                            +"Ваш MAX: "+UserBox.getUser(message.getChatId().toString()).getMax()+"$";
                else mess="Ипользуй помощь /help";
            }

        }
        if(!mess.equals("!"))
        sendMessService.send(message.getChatId().toString(),mess);


    }




}
