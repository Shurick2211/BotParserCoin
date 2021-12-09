package Comand;

import App.*;
import org.telegram.telegrambots.meta.api.objects.Message;


public class RegComand implements Comand{

    private  SendMessService sendMessService;
    private  String mess="Начнем Регистрацию в боте!";
    public RegComand(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        Bot.logger.info("Comand Reg");
        User user=UserBox.getUser(message.getChatId().toString());
        int doReg;
        if (user==null)doReg=0;
        else doReg= user.doRegistration;

        switch (doReg) {
            case 1 : {
                mess = "Введите минимальный курс:";
                user.doRegistration++;
                sendMessService.send(message.getChatId().toString(), mess);
                break;
            }
            case 2 :
            case 22 : {
                double min;
                try {
                    min = Double.parseDouble(message.getText());
                } catch (Exception e) {
                    mess = "Минимум введен не правильно!\nСпробуй ще!";
                    sendMessService.send(message.getChatId().toString(), mess);
                    break;
                }
                assert user != null;
                user.setMin(min);
                if(user.doRegistration==2){
                    mess = "Введите МАКСИМАЛЬНЫЙ курс:";
                    user.doRegistration++;
                }else{
                    mess = "min изменен!";
                    user.doRegistration=0;
                    UserBox.saveUsers(new DataBase());
                }
                sendMessService.send(message.getChatId().toString(), mess);
                break;
            }
            case 3:
            case 33 : {
                double max;
                try {
                    max = Double.parseDouble(message.getText());

                } catch (Exception e) {
                    mess = "МАКСИМУМ введен не правильно!\nСпробуй ще!";
                    sendMessService.send(message.getChatId().toString(), mess);
                    break;
                }
                assert user != null;
                if (max < user.getMin()) {
                    mess = "МАКСИМУМ не может быть меньше минимума\nСпробуй ще!";
                    sendMessService.send(message.getChatId().toString(), mess);
                    break;
                }
                user.setMax(max);
                user.setKurs(ParseKurce.getValuta(user.getValuta()).getKurs());
                if (user.doRegistration==3){
                    user.doRegistration++;
                    mess = "За сколько купили в $:";}
                else {
                    mess="MAX изменен!";
                    user.doRegistration = 0;
                    UserBox.saveUsers(new DataBase());
                }
                sendMessService.send(message.getChatId().toString(), mess);


                break;
            }
            case 4 : {
                double stavka;
                try {
                    stavka = Double.parseDouble(message.getText());

                } catch (Exception e) {
                    mess = "Ставка введена не правильно!\nСпробуй ще!";
                    sendMessService.send(message.getChatId().toString(), mess);
                    break;
                }
                assert user != null;
                user.setStavka(stavka);
                user.setKurs(ParseKurce.getValuta(user.getValuta()).getKurs());
                user.doRegistration++;
                mess ="ДА/Нет="+"Оповещать об измениях курса между min и MAX?";
                sendMessService = new SendMessInline();
                sendMessService.send(message.getChatId().toString(), mess);

                break;
            }
            case 5:{

                mess = "Регистрация завершена!";
                sendMessService.send(message.getChatId().toString(), mess);
                user.doRegistration = 0;
                UserBox.saveUsers(new DataBase());
                System.out.println(user);
                ComandBox comandBox = new ComandBox(new SendMessButton());
                comandBox.useComand("BUTTON").execute(message);
                break;
            }


            default : {
                if (UserBox.getUser(message.getChatId().toString()) == null){
                    UserBox.users.add(new User(message.getChatId().toString(),false, "", 0.0, 0.0, 0.0));
                    user=UserBox.getUser(message.getChatId().toString());}
                else {
                    UserBox.getUser(message.getChatId().toString()).setMin(0.0);
                    UserBox.getUser(message.getChatId().toString()).setMax(0.0);
                    mess = "\n Вы зарегестрированы, меняем ваши данные!";}
                mess += "\nВыберите валюту:";
                user.doRegistration=1;
                StringBuilder name = new StringBuilder();
                ParseKurce.valutas.forEach(v->name.append(v.getName()).append("/"));
                mess = name + "=" + mess;
                sendMessService = new SendMessInline();
                sendMessService.send(message.getChatId().toString(), mess);
            }
        }
        }



    }

