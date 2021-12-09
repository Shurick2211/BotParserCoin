package Comand;

import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpComand implements Comand{
    private final SendMessService sendMessService;


    public HelpComand(SendMessService sendMessService) {
        this.sendMessService = sendMessService;
    }

    @Override
    public void execute(Message message) {
        String mess="Есть команды: \n";
        for (ComandName comandName:ComandName.values()) mess+=comandName.getComandName()+"\n";
        mess=mess.replace("NO","");
        mess=mess.replace("BUTTON","");
        sendMessService.send(message.getChatId().toString(),mess);
    }
}
