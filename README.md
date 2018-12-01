# billing-system
This is a simple billing system that implements the Drools rule engine.


Usage:

java -jar billing-system.jar <DRL file path> <input CSV path> <output CSV path> 

Please note that paths in the command can be relative or absolute.

Example usage:
java -jar billing-system.jar ./Rules.drl ./input.csv ./output.csv

Please note that the paths are positional arguments and therefore must be given in the correct order. 
Also note that the output CSV path contains the name of the output CSV file which does not necessarily exist yet. The output CSV file will be created by the program during execution if it does not exist.

The DRL file a text file with the .drl file extension. It contains business rules that you can customize. The DRL file that is included with this software package contains the following rules:

Item name 		Blue Pen 	Red Pen
Price <= 10 items 	$ 3 		$ 3.5
Price > 10 items 	$ 2.5 		$ 3

If the invoice is larger than $100, a discount of 15% is applied.

The amounts used in the rules can be easily customized in the DRL file.


Your input CSV file must have the following format:
item_name, quantity

The output CSV file contains the invoice in the following format:
One invoice line per row: item_name, item_quantity, tariff, subtotal
Final row: total

Example input
Blue Pen, 5
Red Pen, 11

Example output
Blue Pen, 5, 3.0, 15.0
Red Pen, 11, 3.0, 33.0
48.0


After executing the command given above with the correct arguments (paths) and a correctly structured input CSV file, the output invoice CSV file will be located at your specified path.
