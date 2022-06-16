package App;

import java.io.*;
import java.util.ArrayList;


public class Serial implements UserList {
    private static final String USERSPATH="resources/users.dat";
       public   static Object deserData(String file_name) {
            Object retObject = null;
            try {
                FileInputStream filein = new FileInputStream(file_name);
                ObjectInputStream in = new ObjectInputStream(filein);
                retObject = in.readObject();
                filein.close();
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("файл не найден");
                //System.exit(1);
            } catch (IOException e) {
                System.out.println("ошибка в/вв");
                //System.exit(2);
            } catch (ClassNotFoundException e) {
                System.out.println("класс не найден");
                //System.exit(3);
            }
            return retObject;

        }


      public static   void serData(String file_name, Object obj) {

            try {
                FileOutputStream fileOut=new FileOutputStream(file_name);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(obj);
                out.close();
                fileOut.close();
            } catch (IOException e) {
                System.out.println("ошибка в/вв");
              //  System.exit(2);
            }

        }

    @Override
    public ArrayList<User> users() {
      if(new File(USERSPATH).exists())
            return (ArrayList) deserData(USERSPATH);
      else
      return null;
    }

    @Override
    public void saveUserList(ArrayList<User> users) {
            serData(USERSPATH, users);
    }

    @Override
    public void deletUser(User user) {

    }


}





