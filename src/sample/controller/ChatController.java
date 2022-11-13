package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;


public class ChatController {
    @FXML
    private TextArea txtMessage;
    @FXML
    private TextArea txtHistory;

    @FXML
    private Text txtGetMessage;

    //private PrintWriter printWriter = null;
    //private Scanner scanner= null;

    //private DataOutputStream dos= null;
    //private DataInputStream dis= null;


    private Long port;
    public void init(Long port){
        this.port = Main.username_recipient;
        System.out.println("The ... is http://localhost:"+port);
    }

    /*private void connect(){

        try {
            Socket _client = new Socket("localhost",Math.toIntExact(Main.username_recipient));

            scanner = new Scanner(_client.getInputStream());
            printWriter = new PrintWriter(_client.getOutputStream(),true);
            //Binary
            dis = new DataInputStream(new BufferedInputStream(_client.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(_client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //printWriter = new PrintWriter(new Socket("localhost", Math.toIntExact(port)).getOutputStream(),true);

    }*/
    public void sendMsg(ActionEvent e) throws Exception {
       // port=Main.username_recipient;
        //connect();
        System.out.println("Port is " + Main.username_recipient + " " + Main.username);
        //printWriter
        ConnectController.pw.println("RECEIVE "+Main.username + "|"+ txtMessage.getText() + "|" + Date.from(Instant.now()));


        //new Thread(()->{
            String res = ConnectController.scan.nextLine();
            System.out.println("Response  -- -- "+res);
            txtHistory.appendText(responseMessage(res,"x"));
        //}).start();

    }

    private String responseMessage(String message,String sender){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n\t\t");
        stringBuilder.append(message);
        return stringBuilder.toString();
    }
    public void receiveMsg(ActionEvent e) throws Exception {

    }

    private static class HandleInput implements Runnable{

        private  HandleInput(){

        }
        @Override
        public void run() {

        }
    }


}
