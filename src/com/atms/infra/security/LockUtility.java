package com.atms.infra.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public final class LockUtility {
	
	private static FileOutputStream fos;
	private static FileChannel fchan;
	private static FileLock flock;
	
	private LockUtility () {}
	
	public static synchronized boolean lock() throws Exception {
		
		try {
			fos = new FileOutputStream("C:\\temp\\lockfile");
			fchan = fos.getChannel();
			flock = fchan.tryLock();
		} catch (IOException e) {
			throw e;
		}
		
		if (null == flock) {
			throw new AlreadyConnectedException();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
            	release();
            }
        });
		return true;
	}
	
	public static void release() {
		
		try {
			flock.release();
			fchan.close();
			fos.close();
			new File("C:\\temp\\lockfile").delete();
			System.out.println("end");
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return;
	}
}

