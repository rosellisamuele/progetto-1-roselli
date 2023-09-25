package progetto1roselli;

import java.io.*;
import java.net.*;


public class Server {
    int port = 6567;
    
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione...");

            server = new ServerSocket(port); 
            client = server.accept();
            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server.");
            System.exit(1);
        }

        return client;
    }

    public void comunica(){
        try{
            System.out.println("3 Benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6 Ricevuta la stringa dal cliente: "+stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 Invio la stringa modificata al client ...");
            outVersoClient.writeBytes(stringaModificata+'\n');

            System.out.println("9 SERVER: fine elaborazione.");
            client.close();
        } catch (Exception e){}
    }
}
