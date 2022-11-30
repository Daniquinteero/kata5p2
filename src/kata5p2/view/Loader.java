package kata5p2.view;

import kata5p2.model.Mail;

public interface Loader {
    
    public Iterable<Mail> read(String fileName);
    
}
