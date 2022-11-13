package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import sample.Main;

import java.util.*;

public class LoginController {

    @FXML
    TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private ArrayList<Pair<String,String>> users = new ArrayList<>();

    private Scene scene;

    public void InitSessionId(){

    }

    public void loginClick(ActionEvent actionEvent) throws Exception {


        Random rand = new Random();
        for(int i=0;i<10;i++)
            users.add(new Pair<>(String.valueOf(8080+i),i+""));

        String username =txtUsername.getText();
        String password = txtPassword.getText();

        Main.username = Long.valueOf(username);

        for (Pair<String,String> user: users) {
            if(user.getKey().equals(username)&&
               user.getValue().equals((password))) {
                System.out.println("** Logged In! ***");
                System.out.println("Username : "+user.getKey());
                System.out.println("Redirecting...");
                Main.loadPage("chat.fxml", "CHATTER-"+username, 500, 700, false,false);
                return;
            }
        }

        System.err.println("Invalid information...");
        //Main.loadPage("sample.fxml","Home",500,500,true);
    }

    public void init(Scene scene){
        this.scene=scene;
    }
}
