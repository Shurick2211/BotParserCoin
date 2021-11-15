package App;


import com.vdurmont.emoji.EmojiParser;

public class Main {

    public static void main(String [] args){

    //read users
        UserBox.openUsers(new Serial());
    //запуск бота
        Bot.bot();
    //parse kurs

    ControllKurs controllKurs=new ControllKurs();
        controllKurs.run();

    }


}
