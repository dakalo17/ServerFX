package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.controller.ChatController;

import java.net.URL;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Main extends Application {
    private static Stage primaryStage;

    public static long username =0;
    public static long username_recipient =0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        loadPage("login.fxml","login",300,200,false,!false);

    }


    public static void loadPage(String page, String title, int width, int height, boolean resizable, boolean isScrollable) throws Exception{
        //C:\Users\Gumani\IdeaProjects\ServerFX\src\sample\view\chat.fxml

        AnchorPane root = FXMLLoader.load(Main.class.getResource("/sample/view/" + page));
        //adding menubar
        //root.getChildrenUnmodifiable().add(addDefaultMenu());

        MenuBar menuBar = addDefaultMenu();

        AnchorPane.setRightAnchor(menuBar,0.0);
        AnchorPane.setTopAnchor(menuBar,0.0);
        AnchorPane.setLeftAnchor(menuBar,0.0);
        //AnchorPane.setBottomAnchor(menuBar,100.0);

        root.getChildren().add(menuBar);
        primaryStage.setTitle(title);

        ScrollPane scroll = null;
        Scene scene = null;


        //

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Main.class.getResource("/sample/view/" + page));
//
//        ChatController chatController = loader.getController();
//
//        chatController.init(username);

        if(isScrollable) {
            scroll = new ScrollPane();
            scroll.setContent(root);
            scene = new Scene(scroll, width, height);
        }else{
            scene = new Scene(root, width, height);
        }

        primaryStage.setResizable(resizable);
        //scene.getStylesheets().add(new String("style.css"));//(getClass().getResource("@./resource/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static MenuBar addDefaultMenu(){
        MenuBar menubar = new MenuBar();

        MenuItem miItem = new MenuItem("Connect to User");
        miItem.setOnAction( e->{
            //redirect to connect page
            try {
                loadPage("connect.fxml", "Connect", 500, 500, false, false);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } );

        MenuItem miChat = new MenuItem("Chat with user");
        miChat.setOnAction( e->{
            //redirect to connect page
            try {
                loadPage("chat.fxml", "Chat", 500, 500, false, false);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } );

        Menu mNavigate = new Menu("Navigate");

        mNavigate.getItems().addAll(miItem,miChat);

        menubar.getMenus().add(mNavigate);

        //HBox hbox =new HBox(menubar);
        //hbox.setAlignment(Pos.TOP_CENTER);
        return menubar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
