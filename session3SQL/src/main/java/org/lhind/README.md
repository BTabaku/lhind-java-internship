## LHIND java internship

### [GitHub Repository](https://github.com/BTabaku/lhind-java-internship)


## Session 3

Given the Data structure write the SQL queries to do the following:
- Find the Customer who has the most orders. If more than 1 customer has the most orders then all customers should be displayed.

```sql
SELECT customerNumber, COUNT(orderNumber) AS orderCount
FROM orders
GROUP BY customerNumber
HAVING COUNT(orderNumber) = (
    SELECT MAX(orderCount)
    FROM (
        SELECT COUNT(orderNumber) AS orderCount
        FROM orders
        GROUP BY customerNumber
    ) AS orderCounts
);
result: 141,26
```


- View all “Germany” customers and their order details. If a customer has not made any orders then he should not be included in the result.

```sql
SELECT customerNumber, COUNT(orderNumber) AS orderCount
FROM orders
GROUP BY customerNumber
HAVING COUNT(orderNumber) = (
    SELECT MAX(orderCount)
    FROM (
        SELECT COUNT(orderNumber) AS orderCount
        FROM orders
        GROUP BY customerNumber
    ) AS orderCounts
);
result:

128,"Blauer See Auto, Co.",10101,2003-01-09,Shipped
128,"Blauer See Auto, Co.",10230,2004-03-15,Shipped
128,"Blauer See Auto, Co.",10300,2003-10-04,Shipped
128,"Blauer See Auto, Co.",10323,2004-11-05,Shipped
259,"Toms Spezialitäten, Ltd",10191,2003-11-20,Shipped
259,"Toms Spezialitäten, Ltd",10310,2004-10-16,Shipped
415,"Bavarian Collectables Imports, Co.",10296,2004-09-15,Shipped

```

- List all employees and their revenue amount (based on payments table).

```sql
SELECT e.employeeNumber, e.firstName, e.lastName, SUM(p.amount) AS totalRevenue
FROM employees e
JOIN customers c ON e.employeeNumber = c.salesRepEmployeeNumber
JOIN payments p ON c.customerNumber = p.customerNumber
GROUP BY e.employeeNumber, e.firstName, e.lastName;
result:

1165,Leslie,Jennings,989906.55
1166,Leslie,Thompson,347533.03
1188,Julie,Firrelli,386663.20
1216,Steve,Patterson,449219.13
1286,Foon Yue,Tseng,488212.67
1323,George,Vanauf,584406.80
1337,Loui,Bondur,569485.75
1370,Gerard,Hernandez,1112003.81
1401,Pamela,Castillo,750201.87
1501,Larry,Bott,686653.25
1504,Barry,Jones,637672.65
1611,Andy,Fixter,509385.82
1612,Peter,Marsh,497907.16
1621,Mami,Nishi,457110.07
1702,Martin,Gerard,387477.47

```

- List all products which have been ordered in the last month. (since the database is a bit old we assume we are now at 2005-01-01).

```sql

SELECT p.productCode, p.productName, p.productLine, p.productScale, p.productVendor, p.productDescription, p.quantityInStock, p.buyPrice, p.MSRP
FROM products p
JOIN orderdetails od ON p.productCode = od.productCode
JOIN orders o ON od.orderNumber = o.orderNumber
WHERE o.orderDate BETWEEN '2004-12-01' AND '2004-12-31';

result:

S12_3891,1969 Ford Falcon,Classic Cars,1:12,Second Gear Diecast,Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.,1049,83.05,173.02
S12_4473,1957 Chevy Pickup,Trucks and Buses,1:12,Exoto Designs,"1:12 scale die-cast about 20"" long Hood opens, Rubber wheels",6125,55.70,118.50
S18_2238,1998 Chrysler Plymouth Prowler,Classic Cars,1:18,Gearbox Collectibles,Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.,4724,101.51,163.73


```


- Create a new table named `employeedetails` which should contain data like:
  - `bankAccount`
  - `address`
  - `phoneNumber`
  - `personalEmail`


```sql

CREATE TABLE employeedetails (
    bankAccount VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,
    personalEmail VARCHAR(100) NOT NULL
);

```



### Imported database "classicmodels" - UML

![Exercise 3 UML](../../../../../../lhind-java-internship-main/src/main/resources/custom-data/classicmodels-db.png)
