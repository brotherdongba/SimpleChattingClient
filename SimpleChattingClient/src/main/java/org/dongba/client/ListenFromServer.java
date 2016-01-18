package org.dongba.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ListenFromServer extends Thread {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	
	public ListenFromServer(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.out.flush();
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	@Override
	public void run() {
		while (true) {
			try {
				String msg = (String) in.readObject();
				System.out.println(msg);
				System.out.print("> ");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void close() {
		try {
			this.in.close();
		} catch (IOException e) {
		}
		
		try {
			this.out.close();
		} catch (IOException e) {
		}
		
		try {
			this.socket.close();
		} catch (IOException e) {
		}
	}
	
}
