package fileio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exception.BackupException;

import backbone.Tournament;

public class SerialIO {

	public static void writeTournament(String fileName, Tournament tournament) throws BackupException {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(tournament);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new exception.BackupException(e);
		}
	}

	public static Tournament readTournament(String fileName) throws BackupException {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
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
