package com.boaglio.appmon.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SocketUtil {

	private final static String IP_LOCAL = "127.0.0.1";

	public static boolean available(int port) {
		try (Socket ignored = new Socket(IP_LOCAL,port)) {
			return false;
		} catch (IOException ignored) {
			return true;
		}
	}

	public static Future<Boolean> portIsOpen(final ExecutorService es,final int port,final int timeout) {
		return es.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(IP_LOCAL,port),timeout);
					socket.close();
					// System.out.println(port);
					return true;
				} catch (Exception ex) {
					return false;
				}
			}
		});
	}

	public static void testmain(final String...args) throws InterruptedException,ExecutionException {
		final ExecutorService es = Executors.newFixedThreadPool(20);
		final int timeout = 200;
		final List<Future<Boolean>> futures = new ArrayList<>();
		for (int port = 1 ; port <= 65535 ; port++) {
			futures.add(portIsOpen(es,port,timeout));
		}
		es.shutdown();
		int openPorts = 0;
		for (final Future<Boolean> f : futures) {
			if (f.get()) {
				openPorts++;
			}
		}
		System.out.println("There are " + openPorts + " open ports (probed with a timeout of " + timeout + "ms)");
	}

}
