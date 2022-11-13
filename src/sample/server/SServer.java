package sample.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SServer implements Runnable{

    private ServerSocket serverSocket;
    private Socket client;

    private Scanner scanner;
    private PrintWriter printWriter;

    private DataInputStream dis;
    private DataOutputStream dos;



    private void connect(Socket client){
        this.client = client;

        try{
            //for communication
            scanner = new Scanner(client.getInputStream());
            printWriter = new PrintWriter(client.getOutputStream(),true);

            //for the data
            dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public SServer(Socket client){
        connect(client);
    }

    private void closeConnections(){

    }
    private boolean isRunning=true;
    public void runNorm(){
        try {
            String command = "";
            StringTokenizer stringTokenizer= null;
            System.out.println("Ready to chat....");
            while (true){
                //getting message from client
                command = scanner.nextLine();
                stringTokenizer = new StringTokenizer(command," ");

                String com = stringTokenizer.nextToken();

                String username =  null;
                String message = null;
                String recipientUsername=null;
                String date = null;

                //com == "SEND [MyUsername] [recipientUsername] [message] [date]"
                StringTokenizer st = new StringTokenizer(stringTokenizer.nextToken(),"|");
                if(com.equals("SEND")){

                    username =  st.nextToken();
                    recipientUsername = st.nextToken();
                    message = st.nextToken();
                    date = st.nextToken();

                    System.out.printf("%s is sending to user:%s on %s, \n",
                            username,recipientUsername,new Date(Long.valueOf(date)).toString());
                    //send message
                    String send = String.format("RECEIVE %s %s %s",username,message,date);
                    //send to recipient
                    printWriter.println(send);
                }
                //com == "RECEIVE [username] [message] [date]"
                else if(com.equals("RECEIVE")){
                    username =  st.nextToken();
                    message = st.nextToken();
                    date = st.nextToken();

                    System.out.printf("receiving from user:%s on %s, \n",username,date);
                    printWriter.println("Responding to -- "+message+ " --");
                }else if(com.equals("CLOSE")){
                    isRunning=false;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnections();

        }
    }
    @Override
    public void run() {
        runNorm();
    }
}
