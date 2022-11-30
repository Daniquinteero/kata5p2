package kata5p2.view;

import kata5p2.model.Histograma;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class HistogramDisplay extends ApplicationFrame {
    private final Histograma<String> histogram;
    
    public HistogramDisplay(Histograma<String> histogram, String title) {
        super(title);
        this.histogram = histogram;
        setContentPane(createPanel());
        pack();
    }
    
    public void execute(){
        setVisible(true);
    }

    private JPanel createPanel() {
        ChartPanel chartPanel = new ChartPanel(createChart(createDateSet()));
        chartPanel.setPreferredSize(new Dimension(500, 400));
        return chartPanel;
    }
    
    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("Histogram de emails", 
                                                "dominios de emails",
                                                    "N de emails", dataset,
                                                        PlotOrientation.VERTICAL,
                                                            false, false,
                                                                rootPaneCheckingEnabled);
        return chart;
    }

    private DefaultCategoryDataset createDateSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (String key : this.histogram.keySet()){
            dataset.addValue(this.histogram.get(key), "", key);
        }
        
        return dataset;
    }
}