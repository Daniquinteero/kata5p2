package kata5p2.main;

import java.io.File;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import kata5p2.view.*;
import kata5p2.model.*;

public class Kata5p2 {

    public static void main(String[] args) {
        // Version 2
            
            Iterable<Mail> mailList = new MailListReaderBD().read("kata5.db");
            
            Histograma<String> histogram = new MailHistogramBuilder().build(mailList);
            
            
            
            HistogramDisplay histogramDisplay = new HistogramDisplay(histogram, "HISTOGRAMA");
            histogramDisplay.execute();
            

        
    }
    
}
