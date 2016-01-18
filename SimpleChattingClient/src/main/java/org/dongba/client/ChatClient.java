package org.dongba.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.dongba.model.ChatMessage;
import org.dongba.service.ClientChatService;

public class ChatClient {
	
	private Socket socket;

	private String server, username;
	
	private int port;
	
	private ClientChatService chatService;
	
	public ChatClient(String server, int port, String username) {
		this.server = server;
		this.port = port;
		this.username = username;
		this.chatService = new ClientChatService();
	}
	
	public boolean start() {
		try {
			socket = new Socket(this.server, this.port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		String msg = "Connection accepted " + this.socket.getInetAddress() + ":" + socket.getPort();
		
		display(msg);
		
		try {
			chatService.setListener(new ListenFromServer(this.socket));
			chatService.startListener();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("listener is started.");
		
		try {
			this.chatService.writeMessage(new ChatMessage(0, this.username));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void sendMessage(ChatMessage msg) {
		try {
			this.chatService.writeMessage(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void display(String msg) {
		System.out.println(msg);
	}
	
	private void disconnect() {
		this.chatService.getListener().close();
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int portNumber = 1500;
		String serverAddr = "localhost";
		String username = "Anonymous";
		
		ChatClient client = new ChatClient(serverAddr,	portNumber, username);
		
		if (client.start() == false) {
			return;
		}
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.print("> ");
			String msg = scan.nextLine();
			ChatMessage chatMessage = new ChatMessage(0, msg);
			client.sendMessage(chatMessage);
		}
//		client.disconnect();
		
	}
	
}
