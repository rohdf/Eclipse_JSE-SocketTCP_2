package nsis.socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client extends Thread{
Socket clientSocket;
ArrayList<String> commandeRequete;
 String nomServeur;
InetAddress adresse;

 public Client(String idServeur,ArrayList<String> commande) {
	commandeRequete = commande;
 	nomServeur = idServeur;
	
 	}

 	public Client(String idServeur) {
 		nomServeur = idServeur;
	}

	public void envoyerMessage(ArrayList<String> commande) {
		commandeRequete = commande;

		try{
			for(String commandeStr: this.commandeRequete){
			 System.out.println("Le client à lui même - Je souhaite connaître le numéro de : "+commandeRequete);
			 adresse = InetAddress.getByName(nomServeur);
			 clientSocket = new Socket(adresse,5000);

			 
				//ouverture du flux d'écriture du client
				 BufferedWriter ecriture = new BufferedWriter(
				 new OutputStreamWriter(clientSocket.getOutputStream()));
				 //ouverture du flux de lecture du client
				 BufferedReader lecture = new BufferedReader(
				 new InputStreamReader(clientSocket.getInputStream()));

				 //écriture dans le flux de la question posée au serveur
				 ecriture.write(commandeStr);
				 ecriture.newLine();
				 ecriture.flush();
				 System.out.println("Numéro de " + commandeRequete + " ?");
				 System.out.println("Réponse: " + lecture.readLine());

				 //fermeture des flux
				 ecriture.close();
				 lecture.close();
				 fermer();
			 }
			 
			 
		 }catch(UnknownHostException e) {e.printStackTrace();}
		 catch(IOException e) {e.printStackTrace();}
		
	}

	public void fermer() {
		try {
			//fermeture de la connexion
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 

	@Override
	public void run() {
		this.envoyerMessage(this.commandeRequete);
//		this.fermer();
	}
 
 
 }

 
