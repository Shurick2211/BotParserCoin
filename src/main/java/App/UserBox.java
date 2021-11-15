package App;

import java.util.ArrayList;


public class UserBox {
    public static ArrayList<User>users=new ArrayList<>();

    public static User getUser(String idUser){
        for (User user:users)
            if(user.getChatId().equals(idUser))return user;
        return null;
    }
    public static void openUsers(UserList uL){
        if (uL.users()==null) System.out.println("User List not found!");
        else {
            users = uL.users();
            System.out.println("Users=" + users.size());
        }
    }
    public static void saveUsers(UserList uL){
         uL.saveUserList(users);
    }



}
