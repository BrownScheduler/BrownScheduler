package fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exception.BackupException;

import backbone.Tournament;

/**
 * Handles Saving and Loading Tournaments uses Java Serialization
 */
public class SerialIO {

	/**
	 * Writes a tournament to a file
	 * @param file the file
	 * @param tournament the tournament
	 * @throws BackupException If it cannot save the file
	 */
	public static void writeTournament(File file, Tournament tournament) throws BackupException {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(tournament);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new exception.BackupException();
		}
	}

	/**
	 * Reads a tournament from a file
	 * @param file the file
	 * @return the tournament
	 * @throws BackupException If it cannot read the file or it is not a tournament
	 */
	public static Tournament readTournament(File file) throws BackupException {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (Tournament) in.readObject();
		} catch (IOException e) {
			throw new exception.BackupException();
		} catch (ClassNotFoundException e) {
			throw new exception.BackupException();
		}
	}
}
