# ATM-App



In order to put my skills into practice I have created this ATM app. In the project, I have used the following concepts and constructs:

  * Class

  * Object

  * Method

  * LinkedHashMap

  * For-loop

  * Switch

  * While

etc.



## What does the app do



The app gives the user the option to create a database of bank accounts and do different transactions. At the begging, the user will be asked if he wants to log in with an ID or create an account. If he just starts the program, there are no accounts in the database, so the user's only option will be to create a new account. Each account has:

  * Title - provided by the user

  * First name of the customer - provided by the user

  * Family name of the customer - provided by the user

  * ID - provided by the app. The ID is unique and cannot be repeated.

  * Account balance - starting with 0.

  

After an account is created, the user can choose from 5 actions:

  * Withdraw - where the user can withdraw an amount from their account balance

  * Deposit - where the user can deposit an amount to increase their account balance

  * Balance inquiry - to see what is the current stand of their account balance

  * Transfer - to transfer funds to another existing account

  * Log out - to log out of their account



The user can do multiple actions before logging out. After this, there is the option the continue the application and add other users, so they can transfer funds between each other.



If there is no such desire, the app can be terminated.



## How does the app work



The app is centered around the Class - BankAccount. Each new Bank account Object will be stored in a LinkedHashMap, so it can be reached later. 



When a new account is created it gets an ID. this is a static int in the BankAccount class that increases with 1 each time a new account is created and the value of this int is given to the account as an ID, ensuring that every single account will have a unique ID. 



I have created several methods to help against a repetition of code. Most of them are to make sure, that we receive the requested input for a specific action.

 

