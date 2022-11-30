package kata5p2.view;

import kata5p2.model.Histograma;
import kata5p2.model.Mail;

public class MailHistogramBuilder {

    public Histograma<String> build(Iterable<Mail> list){
        Histograma histogram = new Histograma();
        for (Mail mail : list) {
            histogram.increment(mail.getDomain());
        }
        return histogram;
    }
}
