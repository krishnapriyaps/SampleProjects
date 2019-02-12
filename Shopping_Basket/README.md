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
    * Run command : `mvn clean package` , in `<local-path>SampleProjects/Shopping_Basket/ShoppingBasket` directory where `pom.xml` file is present.
    * Path of Executable java jar will be: 
    `<local-path>/SampleProjects/Shopping_Basket/ShoppingBasket/target/shoppingbasket.jar'
     
* Main Class : com.shoppingbasket.main.EntryPoint
* Command to run the project : 
  `java -jar <git-path>/SampleProjects/Shopping_Basket/ShoppingBasket/target/shoppingbasket.jar PriceBasket item1 item2 item3 ...`

 ### Sample Input and Responses
 
<br /> java -jar /Users/vijaybhaskar1/Desktop/krishna/GitVerify/SampleProjects/Shopping_Basket/ShoppingBasket/target/shoppingbasket.jar PriceBasket Soup Soup Milk Bread Apples

<br /> Subtotal: £4.40
<br /> Bread 50% off: -40p
<br /> Apples 10% off: -10p
<br /> Total: £3.90
