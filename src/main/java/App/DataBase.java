package App;

import org.postgresql.util.PSQLException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataBase implements UserList{
    //  Database credentials
    private  String DB_URL ;
    private  String USER ;
    private  String PASS ;
    private String sql;

    public Statement statementBD(){
        dbConfig();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement=null;
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        if(connection!=null)
            System.out.println("Connected!");
        else System.out.println("Connected is FAIL!");
        try {
            statement=connection.createStatement();
            statement.execute(creatDb());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    private  String creatDb(){
        String create="CREATE TABLE IF NOT EXISTS public.users ("+
    "chatid integer NOT NULL,"+
    "valuta character varying(20), inf boolean,minimum real,"+
    "maximum real,stavka real,CONSTRAINT users_pkey PRIMARY KEY (chatid))";
    return create;
    }




    @Override
    public ArrayList<User> users() {
        ArrayList<User> users=new ArrayList<>();
        sql="select * from users";
        ResultSet resultSet=null;
        try {
            resultSet=statementBD().executeQuery(sql);
            while (resultSet.next()){
                User user=new User(resultSet.getInt("chatID")+"",
                        resultSet.getBoolean("inf"),resultSet.getString("valuta"),
                         resultSet.getDouble("minimum"),resultSet.getDouble("maximum"),
                        resultSet.getDouble("stavka"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    @Override
    public void saveUserList(ArrayList<User> users) {

        for(User user:users){
            sql="insert into users values ('"+Integer.parseInt(user.getChatId())+"','"+user.getValuta()+"','"
                    +user.isInfo()+"','"+user.getMin()+"','"+user.getMax()+"','"+user.getStavka()+"')";
            try {
                statementBD().execute(sql);
            } catch (PSQLException e) {
                try {
                    statementBD().execute("update users set "
                    +"inf="+user.isInfo()+", "+"valuta='"+user.getValuta()+"', "+"minimum="+user.getMin()+", "
                    +"maximum="+user.getMax()+", "+"stavka="+user.getStavka()+"where chatid="
                            +Integer.parseInt(user.getChatId()));

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            } catch (SQLException es) {
                es.printStackTrace();
            }

        }


    }
    private void dbConfig(){
        final String rootPath ="resources/DataBase.properties";
        Properties dbProps = new Properties();
        try {
            dbProps.load(new FileInputStream(rootPath));
            DB_URL=dbProps.getProperty("DB_URL");
            USER=dbProps.getProperty("USER");
            PASS=dbProps.getProperty("PASS");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
//CREATE TABLE IF NOT EXISTS public.users
//(
//    chatid integer NOT NULL,
//    valuta character varying(20) COLLATE pg_catalog."default",
//    inf boolean,
//    minimum real,
//    maximum real,
//    stavka real,
//    CONSTRAINT users_pkey PRIMARY KEY (chatid)
//)