package kata5p2.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;
import kata5p2.model.Mail;

public class MailListReader implements Loader{

    private static final Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public MailListReader() {
    }
    
    
    @Override
    public Iterable<Mail> read(String fileName) {
        return new Iterable<Mail>(){
            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    BufferedReader reader = createReader();
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
                        while (true) {
                            try  {
                                line = reader.readLine();
                            } catch (IOException ex) {
                                return null;
                            }
                            if (line == null) return null;
                            if (isEmail(line)) return new Mail(line);
                        }

                    }
                    
                    private boolean isEmail(String line){
                        return pattern.matcher(line).matches();
                    }
                    
                    private BufferedReader createReader() {
                        try {
                            return new BufferedReader(new FileReader(new File(fileName)));
                        } catch (FileNotFoundException ex) {
                            return null;
                        }
                    }
                };
            }
        };
    } 
}
