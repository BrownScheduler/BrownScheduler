package fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exception.BackupException;

import backbone.Tournament;

public class SerialIO {

	public static void writeTournament(File file, Tournament tournament) throws BackupException {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(tournament);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new exception.BackupException(e);
		}
	}

	public static Tournament readTournament(File file) throws BackupException {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (Tournament) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			throw new exception.BackupException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new exception.BackupException(e);
		}
	}
}
