import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

 public class CurrencyConverter extends JFrame {
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Taxas de câmbio fixas para simplificação
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("BRL", 5.0);
    }

    public CurrencyConverter() {
        setTitle("Conversor de Moedas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel amountLabel = new JLabel("Valor:");
        amountLabel.setBounds(10, 20, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(100, 20, 165, 25);
        panel.add(amountField);

        JLabel fromLabel = new JLabel("De:");
        fromLabel.setBounds(10, 50, 80, 25);
        panel.add(fromLabel);

        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        fromCurrency.setBounds(100, 50, 80, 25);
        panel.add(fromCurrency);

        JLabel toLabel = new JLabel("Para:");
        toLabel.setBounds(10, 80, 80, 25);
        panel.add(toLabel);

        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        toCurrency.setBounds(100, 80, 80, 25);
        panel.add(toCurrency);

        JButton convertButton = new JButton("Converter");
        convertButton.setBounds(10, 110, 150, 25);
        panel.add(convertButton);

        resultLabel = new JLabel("Resultado: ");
        resultLabel.setBounds(10, 140, 300, 25);
        panel.add(resultLabel);

        convertButton.addActionListener(e -> convertCurrency());
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            if (from != null && to != null) {
                double fromRate = exchangeRates.get(from);
                double toRate = exchangeRates.get(to);
                double result = amount * (toRate / fromRate);
                resultLabel.setText(String.format("Resultado: %.2f %s", result, to));
            } else {
                resultLabel.setText("Selecione as moedas.");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Valor inválido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
