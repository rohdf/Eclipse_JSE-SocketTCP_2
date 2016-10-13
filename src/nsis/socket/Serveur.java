package nsis.socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import nsis.dico.Dico;
import nsis.metier.MaThread;

public class Serveur {
 ServerSocket serverSocket;
 Socket clientSocket;
 Dico dico;
 int nbClient;

	public Serveur(String cheminFichierDico){
		nbClient = 0;
		//chargement du dictionnaire
		dico = new Dico(cheminFichierDico);
		try {
			dico.charger();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			System.out.println("Lancement du serveur");
			//association d'un port au service
			serverSocket = new ServerSocket(5000);
			while(true){
				nbClient++;
				System.out.println("\nJe suis en attente d'un client");
				//création d'une connexion
				clientSocket = serverSocket.accept();
				
				MaThread mt = new MaThread(clientSocket, this);
				mt.start();

				
				
			}
//			System.out.println("\nJ'ai eu assez de clients");
		}
		catch(IOException e) {e.printStackTrace();
		}
	}
	
	

 public String get(String clef){
	 System.out.println("Recherche sur la clef : " + clef);
	 return this.dico.consulter(clef);
 }
 
 public synchronized String put(String clef, String valeur){
	 try {
		 System.out.println("Enregistrement de : " + clef+"/"+valeur);
		this.dico.creer(clef, valeur);
		return "Les infos sont saugardées !";
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "ERREUR !! Les infos ne sont pas saugardées !";
	}
 }
}