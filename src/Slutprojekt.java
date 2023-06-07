import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Slutprojekt extends JFrame {
    private Map<String, Double> Rates;
    private JTextField Mängd;
    private JComboBox<String> Från;
    private JComboBox<String> Till;
    private JLabel Resultat;

    public Slutprojekt() {
        Rates = new HashMap<>();
        Rates.put("SEK", 1.0);
        Rates.put("EUR", 0.086);
        Rates.put("GBP", 0.074);
        Rates.put("USD", 0.092);
        Rates.put("YEN", 12.83);

        //Rates.put("custom", 1.0); -- Här kan man lägga till en egen:
        //byt ut custom med valutans namn och 1.0 med värdeskillnaden från SEK till den valutan
        //för att hitta värdeskillnaden från SEK så kan du googla "1 SEK to [valutans namn]"
        
        JLabel mängdLabel = new JLabel("Mängd:");
        Mängd = new JTextField(10);
        JLabel frånLabel = new JLabel("Från:");
        Från = new JComboBox<>(Rates.keySet().toArray(new String[0]));
        JLabel tillLabel = new JLabel("Till:");
        Till = new JComboBox<>(Rates.keySet().toArray(new String[0]));
        JButton konvertera = new JButton("Konvertera");
        Resultat = new JLabel();
        konvertera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(Mängd.getText());
                String fromCurrency = Från.getSelectedItem().toString();
                String toCurrency = Till.getSelectedItem().toString();

                double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
                DecimalFormat df = new DecimalFormat("#.##");
                Resultat.setText(df.format(amount) + " " + fromCurrency + " = " +
                        df.format(convertedAmount) + " " + toCurrency);
            }
        });

        JPanel panel = new JPanel(new GridLayout(8, 10));
        panel.add(mängdLabel);
        panel.add(Mängd);
        panel.add(frånLabel);
        panel.add(Från);
        panel.add(tillLabel);
        panel.add(Till);
        panel.add(konvertera);
        panel.add(Resultat);

        setTitle("Konvertera Valuta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = Rates.get(fromCurrency);
        double toRate = Rates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Slutprojekt converterUI = new Slutprojekt();
                converterUI.setVisible(true);
            }
        });
    }
}
