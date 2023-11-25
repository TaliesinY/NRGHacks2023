import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
public class Converter {
    public static File fileToQuiz(File inputFile, File answerKey) throws Exception {
        int count = 0;
        Scanner fileReader = new Scanner(inputFile);
        Scanner answerKeyReader = new Scanner(answerKey);
        String fileName = fileReader.nextLine() + ".txt";
        File outputFile = new File(fileName);
        PrintWriter filePrintWriter = new PrintWriter(outputFile);
        fileReader.nextLine(); // To account for name and date entries that are common in most tests.
        while (fileReader.hasNextLine()) {
            String checkLine = fileReader.nextLine();
            if (!checkLine.equals("")) {
                if ((checkLine.charAt(1) == '.' || checkLine.charAt(1) == ')')) {
                    filePrintWriter.print(checkLine.substring(3));
                    filePrintWriter.print('^');
                } else if ((checkLine.charAt(1) != '.' && checkLine.charAt(1) != ')') && (count > 0)) {
                    filePrintWriter.print('*' + answerKeyReader.nextLine());
                    filePrintWriter.print("\n" + checkLine);
                    filePrintWriter.print('^');
                } else if ((checkLine.charAt(1) != '.' && checkLine.charAt(1) != ')') && (count == 0)) {
                    filePrintWriter.print(checkLine);
                    filePrintWriter.print('^');
                }
            }
            count += 1;
        }
        if (answerKeyReader.hasNextLine()) {
            filePrintWriter.print('*' + answerKeyReader.nextLine());
        }
        filePrintWriter.close();
        answerKeyReader.close();
        fileReader.close();
        return outputFile;
    }
}