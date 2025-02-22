Use classicmodels;

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


SELECT c.customerNumber, c.customerName, o.orderNumber, o.orderDate, o.status
FROM customers c
JOIN orders o ON c.customerNumber = o.customerNumber
WHERE c.country = 'Germany';


SELECT e.employeeNumber, e.firstName, e.lastName, SUM(p.amount) AS totalRevenue
FROM employees e
JOIN customers c ON e.employeeNumber = c.salesRepEmployeeNumber
JOIN payments p ON c.customerNumber = p.customerNumber
GROUP BY e.employeeNumber, e.firstName, e.lastName;

SELECT p.productCode, p.productName, p.productLine, p.productScale, p.productVendor, p.productDescription, p.quantityInStock, p.buyPrice, p.MSRP
FROM products p
JOIN orderdetails od ON p.productCode = od.productCode
JOIN orders o ON od.orderNumber = o.orderNumber
WHERE o.orderDate BETWEEN '2004-12-01' AND '2004-12-31';



CREATE TABLE employeedetails (
    bankAccount VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL,
    personalEmail VARCHAR(100) NOT NULL
);