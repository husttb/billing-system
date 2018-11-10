package app.service;

import app.factory.KieSessionFactory;
import app.model.Item;
import app.model.Order;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.kie.api.runtime.KieSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceService {

    private final KieSession kieSession;
    private final String inputCSVPath;
    private final String outputCSVPath;

    public InvoiceService(String drlPath, String inputCSVPath, String outputCSVPath) throws IOException {
        kieSession = KieSessionFactory.getKieSession(drlPath);
        this.inputCSVPath = inputCSVPath;
        this.outputCSVPath = outputCSVPath;
    }

    public void init() throws IOException {
        List<Item> items = setItemPrices(inputCSVPath);
        Order order = calculateTotal(items);
        order = applyDiscount(order);
        createInvoiceCSV(order, outputCSVPath);
    }

    private List<Item> setItemPrices(String inputPath) throws IOException {
        Iterable<CSVRecord> records = CSVUtil.getCSVRecords(inputPath);

        List<Item> items = new ArrayList<>();

        for (CSVRecord record : records) {
            String name = record.get(0).trim();
            Integer quantity = Integer.parseInt(record.get(1).trim());
            Item item = new Item(name, quantity);
            kieSession.insert(item);
            items.add(item);
        }

        kieSession.fireAllRules();

        return items;
    }

    private Order calculateTotal(List<Item> items) {
        Order order = new Order(items);
        order.calculateTotal();

        return order;
    }

    private Order applyDiscount(Order order) {
        kieSession.insert(order);
        kieSession.fireAllRules();

        order.applyDiscount();

        return order;
    }

    private void createInvoiceCSV(Order order, String outputPath) throws IOException {
        CSVPrinter csvPrinter = CSVUtil.getCSVPrinter(outputPath);

        for (Item item : order.getItems()) {
            csvPrinter.printRecord(item.getName(), item.getQuantity(), item.getPrice(), item.getSubtotal());
        }

        csvPrinter.printRecord(order.getTotal());
        csvPrinter.flush();
    }

}
