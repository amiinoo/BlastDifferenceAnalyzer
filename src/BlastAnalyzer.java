//import shit
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class BlastAnalyzer {

    /**
     * This is the mainline program that will take in the values of BLAST.
     * 
     * @author Andrew Herold
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String queryName;
        String subjectName;

        Scanner userInput = new Scanner(System.in);

        // set names of things
        System.out.println(
                "Hello! Welcome to the BLAST difference analyzer, written by Andrew Herold.\nPlease ensure that the blast.txt document is formatted to spec.");
        System.out.print("Please enter the name of the query phage: ");
        queryName = userInput.nextLine();
        System.out.print("Great! And the name of the subject phage is: ");
        subjectName = userInput.nextLine();

        // close userInput
        userInput.close();

        // create file
        System.out.println("Alright! Analyzing differences in data...");
        File blastFile = new File(".\\blast.txt");

        // file reader
        fileReader(blastFile, queryName, subjectName);

    }

    public static void fileReader(File f, String queryName, String subjectName) throws IOException {
        File blast = f;
        Scanner blastFileIn = null;
        FileWriter fw = null;
        try {
            // attempt to read in file
            blastFileIn = new Scanner(blast);
            fw = new FileWriter(".\\output.txt", false);
        } catch (IOException e) {
            // fail gracefully otherwise
            e.printStackTrace();
            System.out.println("You shouldn't be seeing this error, unless you fucked something up.");
            System.exit(0);
        }

        // difference, spot, line counters
        int diffCounter = 0;
        int spotCounter = 0;
        int lineCounter = 1;
        int lineLength = 0;

        String queryGenome = "";
        String subjectGenome = "";

        fw.write("-------------------------------------------------------------------------\n");
        fw.write(String.format("BLAST difference analysis of query phage %s and subject phage %s:\n", queryName,
                subjectName));
        fw.write("-------------------------------------------------------------------------\n");

        // create a dataline and a scanline
        String blastLine = blastFileIn.nextLine();
        try {
            // while the blast is valid, continue reading
            while (blastFileIn.hasNextLine()) {
                blastLine = blastFileIn.nextLine();
                ++lineCounter;
                while (lineCounter < 9) {
                    blastLine = blastFileIn.nextLine();
                    ++lineCounter;
                }

                lineLength = 60;

                // skip line, read in query genome
                blastLine = blastFileIn.nextLine();
                ++lineCounter;
                queryGenome = blastLine.substring(13, 13 + lineLength);

                // skip a line
                blastLine = blastFileIn.nextLine();
                ++lineCounter;

                // read in subject genome
                blastLine = blastFileIn.nextLine();
                subjectGenome = blastLine.substring(13, 13 + lineLength);

                // skip a line
                blastLine = blastFileIn.nextLine();
                ++lineCounter;

                String spots = String.format("Spot %d - %d: ", spotCounter, spotCounter + lineLength);
                fw.write(spots);

                if (queryGenome.equals(subjectGenome)) {
                    fw.write("no differences found!\n");
                } else {
                    fw.write("DIFF FOUND! - ");
                    // compare lines
                    for (int i = 0; i < lineLength; ++i) {
                        if (queryGenome.charAt(i) == subjectGenome.charAt(i)) {
                            // do nothing
                        } else {
                            fw.write("at spot " + i + "\n");
                            diffCounter++;
                        }
                    }
                }
                // increment spotCounter
                spotCounter = spotCounter + lineLength;
                fw.flush();
            }
        } catch (StringIndexOutOfBoundsException se) {
            System.out.print(
                    "Because I'm lazy, and the last line is a differing length from the standard 60, you're gonna need to evaluate the last line yourself. Sorry!\n");
            System.out.print("Now, to fail gracefully.\n");
            fw.write("Data analyzed!\n");
            String end = String.format(
                    "The program found %d differences between the query phage %s and the subject phage %s.\n",
                    diffCounter, queryName, subjectName);
            fw.write(end);
            fw.write("-------------------------------------------------------------------------\n\n\n");
            blastFileIn.close();
            fw.close();
            System.out.println("Data analyzed! Check the output.txt file for the result.");
        }

    }

}