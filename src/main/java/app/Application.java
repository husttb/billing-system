package app;


import app.service.InvoiceService;

public class Application {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            throw new Exception("Enter all three programs arguments: 1.path to your rules file 2.path to input csv file 3.path to output csv file");
        }

        String drlPath = args[0];
        String inputCSVPath = args[1];
        String outputCSVPath = args[2];

        InvoiceService invoiceService = new InvoiceService(drlPath, inputCSVPath, outputCSVPath);
        invoiceService.init();
        System.out.println("Success! Please find your csv output file at the specified path.");
    }
}
