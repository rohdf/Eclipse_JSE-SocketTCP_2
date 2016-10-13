package nsis.metier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import nsis.socket.Serveur;

public class MaThread extends Thread {
	Socket clientSocket;
	Serveur serv;
	
	public MaThread(Socket clientSocket, Serveur serv) {
		this.clientSocket = clientSocket;
		this.serv = serv;
	}

	@Override
	public void run() {
		BufferedWriter ecriture = null;
		BufferedReader lecture = null;
		try {
		//ouverture du flux d'écriture du serveur
		ecriture = new BufferedWriter(
		new OutputStreamWriter(clientSocket.getOutputStream()));
		//ouverture du flux de lecture du serveur
		lecture = new BufferedReader(
		new InputStreamReader(clientSocket.getInputStream()));
		
			//lecture du produit demandé.
			String str = lecture.readLine();
			
			StringTokenizer st = new StringTokenizer(str," ");
			
			String reponse = null;
			 
			switch (st.nextToken()) {
				case "GET":	reponse = serv.get(st.nextToken());
				 			break;
				case "PUT":	reponse = serv.put(st.nextToken(), st.nextToken());
							break;
				default: 	reponse = "Erreur dans la commande envoyée";	
							break;
			}
		 
		 
		
			ecriture.write(reponse);
			ecriture.newLine();
			ecriture.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ecriture.close();
				lecture.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
