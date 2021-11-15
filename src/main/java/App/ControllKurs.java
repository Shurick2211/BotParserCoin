package App;

import Comand.SendMess;
import com.vdurmont.emoji.EmojiParser;

import java.io.IOException;
import java.text.DecimalFormat;

public class ControllKurs extends Thread{

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                ParseKurce.getValutes();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (User user:UserBox.users)
                for (Valuta valuta:ParseKurce.valutas){
                    if (user.getValuta().equals(valuta.getName())) {
                        if(user.getKurs()==0.0)user.setKurs(valuta.getKurs());
                        double proc;
                        String mess="Вы купили:"+user.getValuta()+"="+user.getStavka()+"$\n";
                        String messNull=mess;
                        //min
                        if (user.getMin()>=valuta.getKurs()&user.getMin()>0){
                            proc=(user.getMin()-valuta.getKurs())/user.getMin()*100;
                            mess+="Курс "+valuta+" упал ниже Вашего минимума("+user.getMin()+"$) на "+
                                    new DecimalFormat("#0.0##").format(proc)+"%";
                            }
                        //max
                        if (user.getMax()<=valuta.getKurs()&user.getMax()>0){
                            proc=(valuta.getKurs()-user.getMax())/user.getMax()*100;
                            mess+="Курс "+valuta+" вырос выше Вашего Максимума("+user.getMax()+"$) на "+
                                    new DecimalFormat("#0.0##").format(proc)+"%";
                            }
                        //
                        if(user.getMin()<valuta.getKurs()
                                &user.getMax()>valuta.getKurs()
                                &user.getMin()>0
                                &user.getMax()>0
                                &user.isInfo()){
                            if(valuta.getKurs()<user.getKurs()){
                                proc=(user.getKurs()-valuta.getKurs())/user.getKurs()*100;
                                mess+=EmojiParser.parseToUnicode(":arrow_down:")+ " "
                                        +valuta+ "\n на:"+new DecimalFormat("#0.0##").format(proc)+"%";
                                user.setKurs(valuta.getKurs());
                            }
                            if(valuta.getKurs()>user.getKurs()){
                                proc=(valuta.getKurs()-user.getKurs())/user.getKurs()*100;
                                mess+= EmojiParser.parseToUnicode(":arrow_up:")+ " "
                                        +valuta+ "\n на:"+new DecimalFormat("#0.0##").format(proc)+"%";
                                user.setKurs(valuta.getKurs());
                            }
                        }


                        if (user.doRegistration==0&
                                !mess.equals(messNull))
                        new SendMess().send(user.getChatId(),mess);
                    }

                }

            try {
                sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
