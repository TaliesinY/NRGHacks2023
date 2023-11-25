import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
public class Converter {
   public static File fileToQuiz(File inputFile, File answerKey) throws Exception{
       int QNum = 0;
       String currentLine;
       int indexingSize = 0;


       Scanner fileReader = new Scanner(inputFile);
       Scanner answerKeyReader = new Scanner(answerKey);




       String fileName = fileReader.nextLine()+".txt";
       File outputFile = new File(fileName);
       PrintWriter filePrintWriter = new PrintWriter(outputFile);




       while(fileReader.hasNextLine()){


           currentLine = fileReader.nextLine();
           currentLine = currentLine.trim();

           if (currentLine.length() > 0) {
               if ((Character.isDigit(currentLine.charAt(0))) || (Character.isLowerCase(currentLine.charAt(0)))) {

                   if (Character.isDigit(currentLine.charAt(0))) {


                       while (Character.isDigit(currentLine.charAt(indexingSize))) {
                           indexingSize++;
                       }
                       indexingSize += 2;

                       currentLine = currentLine.substring(indexingSize);
                       currentLine = currentLine.trim();


                       if (QNum > 0) {
                           filePrintWriter.print("^" + answerKeyReader.nextLine());
                           filePrintWriter.print("\n");
                       }


                       filePrintWriter.print(currentLine);
                       QNum = QNum +  1;


                   } else if (Character.isLowerCase(currentLine.charAt(0))) {
                       filePrintWriter.print('^');

                       while (Character.isLowerCase(currentLine.charAt(indexingSize))) {
                           indexingSize++;
                       }
                       indexingSize += 2;

                       currentLine = currentLine.substring(indexingSize);
                       currentLine = currentLine.trim();

                       filePrintWriter.print(currentLine);
                   }
               }
           }

           indexingSize = 0;
       }

       if (answerKeyReader.hasNextLine()) {
           filePrintWriter.print('^' + answerKeyReader.nextLine());
       }

       filePrintWriter.close();
       answerKeyReader.close();
       fileReader.close();


       return outputFile;
   }
}
