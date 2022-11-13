package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;
import sample.server.SServer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ConnectController {

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtConnect;


    private int username_recipient;
    public void waitconnect(ActionEvent e) throws Exception {
        int username_recipient = Integer.parseInt(txtConnect.getText());

        this.username_recipient = username_recipient;

        Main.username_recipient = Long.valueOf(username_recipient);
        System.out.println("Th evvvv" + Main.username_recipient);
        if(connectClient(username_recipient))
            Main.loadPage("chat.fxml",Main.username+"-Chatting with-"+username_recipient,
                500,500,false,false);
    }
    public void connect(ActionEvent e) throws Exception{

        System.out.println("Connecting..");
        if( lblStatus != null) {

            if(lblStatus.getStyle().equals("-fx-text-fill:green;")) {
                lblStatus.setStyle("-fx-text-fill:red;");
            }else{
                lblStatus.setStyle("-fx-text-fill:green;");
            }

            //Main.loadPage("connect.fxml","Connect",500,500,false,false);
        }

        //int username_recipient = Integer.parseInt(txtConnect.getText());
        Main.username_recipient = Long.valueOf(txtConnect.getText());
        System.out.println("The connecting to "+ Main.username_recipient );

        new Thread(() -> connectToServer((int)Main.username_recipient)).start();

    }


    //server itself
    private static boolean connectToServer(Integer username_recipient){

        ServerSocket serv = null;

        try {
            serv = new ServerSocket(username_recipient);

            while (true){
                Socket client = serv.accept();
                System.out.printf("Client connected to %s ! !\n", username_recipient.toString());
                SServer server = new SServer(client);
                System.out.println("Threading #"+Thread.currentThread());
                server.runNorm();
                //new Thread(server).start();

                System.out.println("Threading #"+Thread.currentThread());
                BiFunction<Long,String,Integer> func = (username,page) -> {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("/sample/view/" + page));

                    ChatController chatController = loader.getController();

                    chatController.init(username);
                    return 1;
                };

                func.apply(Long.valueOf(username_recipient),"chat.fxml");
                Main.loadPage("chat.fxml",Main.username+"-Chatting with-"+username_recipient,
                        500,500,false,false);



            }

        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                if(serv != null)
                    serv.close();
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return true;
        }


    }

    public static Socket client;
    public static DataInputStream dis;
    public static DataOutputStream dos;

    public static PrintWriter pw;
    public static Scanner scan;


    public static boolean connectClient(Integer username){
        try{
            client = new Socket("localhost",username);
            System.out.println("Connected to server");

            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());

            pw = new PrintWriter(client.getOutputStream(),true);
            scan = new Scanner(client.getInputStream());

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
