# Shopping Basket

The program accept a list of items in the basket and output the subtotal, the special offer discounts and the final price. The program accepts input from commandline and response is formatted and printed

### Assumptions Made:
List of items, price and offers are provided along with configuration as JSON

### Dependencies:
* log4j-1.2.17.jar
* gson-2.6.2.jar

### To run the project:
* Create jar of the project 
* Main Class : com.shoppingbasket.main.EntryPoint
* Command to run the project : 
  *java -cp <path to log4j-1.2.17.jar>:<path to gson-2.6.2.jar>:<path to project jar> com.shoppingbasket.main.EntryPoint PriceBasket item1 item2 item3 ...*

 ### Sample Input and Responses
 
<br /> java -cp /Users/xyz/jar/log4j-1.2.17.jar:/Users/xyz/jar/gson-2.6.2.jar:/Users/xyz/jar/shoppingbasket.jar com.shoppingbasket.main.EntryPoint PriceBasket Soup Soup Milk Bread Apples

<br /> Subtotal: £4.4
<br /> Bread 50% off: -40p
<br /> Apples 10% off: -10p
<br /><br />
<br /> Total: £3.9
