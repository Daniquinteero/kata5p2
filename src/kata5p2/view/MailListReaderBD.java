package kata5p2.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import kata5p2.model.Mail;

public class MailListReaderBD implements Loader{

    private static final Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public MailListReaderBD() {
    }
    
    @Override
    public Iterable<Mail> read(String database) {
        
        return new Iterable<Mail>(){
            @Override
            public Iterator<Mail> iterator() {
                
                return new Iterator<Mail>() {
                    
                    ResultSet res = databaseConnection();
                    Mail nextEmail = nextEmail();
                    
                    @Override
                    public boolean hasNext() {
                        
                        return nextEmail != null;
                    }

                    @Override
                    public Mail next() {
                        Mail email = nextEmail;
                        nextEmail = nextEmail();
                        return email;
                    }
                    
                    private Mail nextEmail(){
                        String line;
                        try {
                            while (res.next()) {
                                line = res.getString(2);
                                if (line == null) return null;
                                if (isEmail(line)) return new Mail(line);
                            }
                        } catch (SQLException ex) {
                            return null;
                        }
                        return null;
                    }
                    
                    private boolean isEmail(String line){
                        return pattern.matcher(line).matches();
                    }
                    
                    private ResultSet databaseConnection() {
                        
                        ResultSet resultSet = null;
                        try {
                            Connection conn = DriverManager.getConnection("jdbc:sqlite:kata5.db");
                            Statement statement = conn.createStatement();
                            resultSet = statement.executeQuery("select * from email");
                            
                        } catch (SQLException ex) {
                            ex.getMessage();
                        }
                        
                        return resultSet;
                    }
                };
            }
        };
    } 
}