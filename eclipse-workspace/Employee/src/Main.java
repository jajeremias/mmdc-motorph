import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class Main extends JFrame {
    private DefaultTableModel tableModel;
    private JTable dataTable;

    private static final String FILE_PATH = "employeeDetails.csv";

    public Main() {
        initializeUI();
        loadData();
    }

    private void initializeUI() {
        setTitle("CSV Read GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Employee No.");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Birthday");
        tableModel.addColumn("SSS No.");
        tableModel.addColumn("Philhealth No.");
        tableModel.addColumn("TIN No.");
        tableModel.addColumn("Pag-ibig No.");
        dataTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(dataTable);

        // Panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tableScrollPane, BorderLayout.CENTER);
        setContentPane(panel);
    }

    private void loadData() {
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            String[] nextLine;
            try {
				while ((nextLine = reader.readNext()) != null) {
				    if (nextLine.length >= 10) {
				        String[] rowData = new String[8];
				        rowData[0] = nextLine[0];
				        rowData[1] = nextLine[1];
				        rowData[2] = nextLine[2];
				        rowData[3] = nextLine[3];
				        rowData[4] = nextLine[6];
				        rowData[5] = nextLine[7];
				        rowData[6] = nextLine[8];
				        rowData[7] = nextLine[9];
				        tableModel.addRow(rowData);
				    }
				}
			} catch (CsvValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}