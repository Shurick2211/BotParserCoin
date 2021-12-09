package Comand;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Comand {

    void execute(Message message);
}
