package me.krodnar.sevenkey.tools;

import me.krodnar.sevenkey.models.Chord;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChordReader {

	private static String regex = "(^.+)\\s*=|(\\d)";
	private static Pattern pattern = Pattern.compile(regex);

	private static String currentType;

	public static List<Chord> readChords(URL location) throws IOException {
		if (location == null) {
			throw new NullPointerException("Location is null");
		}

		return readChords(location.openStream());
	}

	private static List<Chord> readChords(InputStream file) throws IOException {
		List<Chord> chords = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		String line = reader.readLine();

		while (line != null) {
			if (line.startsWith("#")) {
				currentType = line.substring(1);
			} else if (!line.isEmpty()) {
				Chord chord = parseChord(currentType, line);
				chords.add(chord);
			}

			line = reader.readLine();
		}

		reader.close();

		return chords;
	}

	private static Chord parseChord(String type, String line) {
		String naming = "";
		List<Integer> steps = new ArrayList<>();
		steps.add(0);

		final Matcher matcher = pattern.matcher(line);

		while (matcher.find()) {
			String subType = matcher.group(1);
			String step = matcher.group(2);

			if (subType != null) {
				naming = subType;
			} else {
				steps.add(Integer.parseInt(step));
			}
		}

		return new Chord(type, naming, 0, steps.stream().mapToInt(i -> i).toArray());
	}
}
