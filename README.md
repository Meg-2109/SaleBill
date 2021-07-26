# SalesBill Generation using Java

**This Java application is built for following purposes**:

-> To access the data from database.

-> Adding the product information on to the bill.

-> Generating the bill.

**Technologies Used:**

->Java

->JDBC

->MySQL

**Dummy Database Initialization**

STEP 1: Open MySQL Command Prompt or MySQL Workbench

STEP 2: Login to the administrator user as : mysql -u <username> -p (Enter Password if asked)

STEP 3 :Copy paste the following MySql Commands:
  
          > create database test; 
  
          > use test;
  
          > create table product(id varchar(10) primary key, prodname varchar(20), price int);

          > insert into product values('P001’, ‘Cake’, 5);

          > insert into product values('P002’, ‘Juice’, 4);

          > insert into product values('P003’, ‘Chocos’, 6);

          > insert into product values('P004’, ‘Burger’, 10);

          > insert into product values('P005’, ‘Banana’, 3);

          > insert into product values('P006’, ‘Tomato’, 3);

          > commit;

  
**Follow the steps below to import and execute**
  
  Step 1: Install MySQL or MySQL workbench. Create the database as described in Dummy Database Initialization.

  Step 2: Open Eclipse in Java perspective. [Install, if not already installed. Supported in Java 1.2 and above versions]

  Step 3: Click On File > Import > Git > Projects From Git > Clone Uri > Copy the Url: “https://github.com/Meg-2109/SaleBill.git” > Select Java Branch > Next > Next > Finish

  Step 4: Right click on imported project > Run As > Java Application

  Step 5: Give the product Id [e.g., P001] and press enter and select the quantity.
  
 ![image](https://user-images.githubusercontent.com/87992979/127057737-b3fe7cba-1e82-4006-8e4e-3f70e206fe90.png)

  Step 6: Click on Add button to Add the product details into the billing table.
 
  ![image](https://user-images.githubusercontent.com/87992979/127057784-c7e4009f-97a3-4331-8a49-1b65060c6585.png)

  Step 7: Enter the amount paid by customer at Pay text field.
  
  Step 8: Click Print button to generate the bill.

  ![image](https://user-images.githubusercontent.com/87992979/127057807-8cf53d05-166e-41d3-b6fb-25522bccf4b2.png)
  
