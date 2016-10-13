package nsis.app;

import java.io.IOException;
import java.util.ArrayList;

import nsis.socket.Client;

public class ClientApp {

	public static void main(String[] args) {
		
		Client client = new Client("localhost");
		ArrayList<String> messages = new ArrayList<>();
		messages.add("GET nana");
		messages.add("GET koko");
		messages.add("GET toto");
		messages.add("GET tutu");
		messages.add("GET titi");
		
		client.envoyerMessage(messages);
		//client.fermer();
		
		ArrayList<String> messages1 = new ArrayList<>();
		ArrayList<String> messages2 = new ArrayList<>();
		ArrayList<String> messages3 = new ArrayList<>();
		ArrayList<String> messages4 = new ArrayList<>();
		ArrayList<String> messages5 = new ArrayList<>();
		
		for(int i=0; i<10; i++){
			messages1.add("GET frank");
			messages1.add("GET rohdri");
			messages1.add("GET loic");
			messages1.add("GET nana");
			
			
			
			messages2.add("GET frank");
			messages2.add("GET rohdri");
			messages2.add("GET loic");
			messages2.add("GET nana");
			
			
			
			
			messages3.add("GET frank");
			messages3.add("GET rohdri");
			messages3.add("GET loic");
			messages3.add("GET nana");
			
			
			
			
			messages4.add("GET frank");
			messages4.add("GET rohdri");
			messages4.add("GET loic");
			messages4.add("GET nana");
			
			
			
			messages5.add("GET frank");
			messages5.add("GET rohdri");
			messages5.add("GET loic");
			messages5.add("GET nana");
		}
		
		
		
		ArrayList<Client> clients = new ArrayList<>();
		
		//Initialisation des clients
		clients.add(new Client("192.168.10.213", messages1));
		clients.add(new Client("192.168.10.213", messages2));
		clients.add(new Client("192.168.10.213", messages3));
		clients.add(new Client("192.168.10.213", messages4));
		clients.add(new Client("192.168.10.213", messages5));
		
		for(Client c: clients){
			c.start();
		}
		
		

	}
  
}
