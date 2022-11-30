package kata5p2.main;

import java.io.File;
import kata5p2.view.*;
import kata5p2.model.*;

public class Kata5p2 {

    public static void main(String[] args) {
        // Version 1
        
        Iterable<Mail> mailList = new MailListReader().read("emails.txt");
        
        Histograma<String> histogram = new MailHistogramBuilder().build(mailList);
        
        
        
        HistogramDisplay histogramDisplay = new HistogramDisplay(histogram, "HISTOGRAMA");
        histogramDisplay.execute();
        
    }
    
}
