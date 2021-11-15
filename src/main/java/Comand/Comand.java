package Comand;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public interface Comand {

    void execute(Message message);
}
