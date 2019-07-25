package MAA.ConstraintLayoutParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import MAA.ConstraintLayoutParser.structure.ConstraintLayout;

public class App {

	/**
	 * <h1>Main-Methode für den Betrieb auf der Kommandozeile</h1> Mit
	 * <code>mvn package</code> wird eine fertige Version mit allen Abhängigkeiten
	 * gebaut. Hier sind folgende Parameter möglich:
	 * <ul>
	 * <li>-i gefolgt von der Angabe des Eingabedatei
	 * <li>-o gefolgt von der Angabe der Ausgabedatei. Diese Option ist optional da
	 * der Default output.java ist. Die Java-Klasse heißt equivalent
	 * </ul>
	 * 
	 * @param args Argumente für die Bearbeitung
	 *             <code>-i "InputFile" -o "Outputfile"</code>
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		
		CommandLine cmd = InitCommandLineParser(args);
		cmd.getOptionValue("input");
		
		
		ConstraintLayoutParser parser = new ConstraintLayoutParser(cmd.getOptionValue("input"));
		ConstraintLayout parseLayout = parser.parseLayout(cmd.getOptionValue("output", "output.java"));
		writeFile(cmd.getOptionValue("output", "output.java"), parseLayout.export());
	}

	/**
	 * Main-Methode zum Testen in der IDE
	 * 
	 * @param args Egal werden in der Version nicht benötigt
	 * @throws IOException
	 */

	/*public static void main(String[] args) throws IOException {
		ConstraintLayoutParser parser = new ConstraintLayoutParser("example/test_main.xml");
		ConstraintLayout parseLayout = parser.parseLayout("test_main");
		writeFile("test.java", parseLayout.export());
	}*/

	/**
	 * <h1>Ausgabe-Methode</h1> Schreib eine Datei auf die Platte
	 * 
	 * @param fileName Dateiname der Datei
	 * @param output   Content
	 * @throws IOException
	 */
	private static void writeFile(String fileName, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(output);
		writer.close();
	}

	/**
	 * <h1>Konfigurations-Methode CLI</h1> Initialisiert den
	 * <b>CommandLineParser</b> mit seinen Argumenten
	 * 
	 * @param args Übergabeparameter aus main
	 * @return Commandline mit extrahierten Parametern
	 */
	private static CommandLine InitCommandLineParser(String[] args) {
		Options options = new Options();

		Option input = new Option("i", "input", true, "input file path");
		input.setRequired(true);
		options.addOption(input);

		Option output = new Option("o", "output", true, "output file");
		output.setRequired(false);
		options.addOption(output);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

		return cmd;
	}

}
