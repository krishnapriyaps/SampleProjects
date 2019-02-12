# Shopping Basket

The program accept a list of items in the basket and output the subtotal, the special offer discounts and the final price. The program accepts input from commandline and a formatted string will be response. This project uses [GitHub Maven Plugins](https://github.com/github/maven-plugins) to build the application.

### Assumptions Made:
* List of items, price and offers are provided along with configuration as JSON

### Dependencies:
* log4j-1.2.17.jar
* gson-2.6.2.jar

### To run the project:
* To build jar: 
    * Install maven, refer <a>https://maven.apache.org/install.html</a>
    * Git clone this project or Download this project to local directory. Let's assume `<local-path>` be the local path there you place the project.
    * In command line change directory to `<local-path>SampleProjects/Shopping_Basket/ShoppingBasket` directory where `pom.xml` file is present.
    * Run command : `mvn clean package` 
    * Path of Executable java jar will be: 
    `target/shoppingbasket.jar'
     
* Main Class : com.shoppingbasket.main.EntryPoint
* Command to run application - stay in `<local-path>SampleProjects/Shopping_Basket/ShoppingBasket` directory and run command - 
  `java -jar target/shoppingbasket.jar PriceBasket item1 item2 item3 ...`

 ### Sample Input and Responses
 
```
java -jar target/shoppingbasket.jar PriceBasket Soup Soup Milk Bread Apples
Subtotal: £4.40
Bread 50% off: -40p
Apples 10% off: -10p
Total: £3.90

java -jar target/shoppingbasket.jar PriceBasket Soup Milk Bread Apples
Subtotal: £3.75
Apples 10% off: -10p
Total: £3.65 

java -jar target/shoppingbasket.jar PriceBasket Soup Milk Bread Bread
Subtotal: £3.55
(No offers available)
Total price: £3.55
```
