# EasyShop E-Commerce Spring Boot API

## Project Description

With this application, the user is able to utilize both a web browser and Postman to navigate through the online store "EasyShop"
and have the options to:

- View all products
- View all categories
- Filter through products based on price

Additionally, the user will be able to log in to the website where they will be able to:

- Access their user profile
- Add products to their shopping cart

If the user logs in as admin through Postman, they will have additional features that are restricted to only admins of the website,
such as:

- Adding a new product category
- Deleting a product category

## Home Screen

On the homepage of EasyShop, the user is shown a list of products that are available at the store. There, the user is 
to filter the products based on 

- Product category
- Price
- Color

The user can choose to filter by one of the available filters or select multiple at once, 
which will then be applied and reflected in realtime through the displayed products in front of them.


><details>
><summary> Home Screen </summary>
>
> IMAGE HERE
>
></details>

><details>
><summary> Filters </summary>
>
>><details>
>><summary> Filtering By Category </summary>
>>
>>IMAGE HERE
>></details>
>
>><details>
>><summary> Filtering By Price </summary>
>>
>>IMAGE HERE
>></details>
>
>><details>
>><summary> Filtering By Color </summary>
>>
>> IMAGE HERE
>></details>
>
>><details>
>><summary> Applying All Filters </summary>
>>
>> IMAGE HERE
>></details>
></details>

## User Profile

Additionally, there is a login option available on the right side of the home page, which will prompt the user to enter
in their username and password. Once they have successfully logged in, they are now able to:

- Modify their profile details
- Add products to their shopping cart

><details>
><summary> Logging In </summary>
>
>><details>
>><summary> Login Prompt </summary>
>>
>> IMAGE HERE
>></details>
>
>><details>
>><summary> After Logging In </summary>
>>
>> IMAGE HERE
>></details>
></details>

><details>
><summary> Modifying Profile Details </summary>
>
>><details>
>><summary> Before Modifying Profile </summary>
>>
>> IMAGE HERE
>>
>></details>
>
>><details>
>><summary> After Modifying Profile </summary>
>>
>> IMAGE HERE
>>
></details>
></details>

><details>
><summary> Adding a Product to cart </summary>
>
> IMAGE HERE
>
></details>

## Admin Profile

Using Postman, the user is able to modify the online store by:

- Add a new product category
- Remove a product category

><details>
><summary> Adding a Category </summary>
>
>><details>
>><summary> New Category On Postman </summary>
>>
>> IMAGE HERE
>></details>
>
>><details>
>><summary> New Category On Website </summary>
>>
>> IMAGE HERE
></details>
></details>

><details>
><summary> Removing a Category </summary>
>
> IMAGE HERE
>
></details>

## Interesting Piece of Code

```
    String query = "SELECT * FROM shopping_cart AS s " +
            "JOIN products AS p " +
            "ON p.product_id = s.product_id " +
            "WHERE user_id = ?";
    
    try(Connection connection = getConnection()) {
    
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
```

I found it interesting how interconnected the Shopping Cart and Products data was. In this piece of code, we are
utilizing the 'getById' method in the ShoppingCartDAO in order to be able to retrieve a user's id and pull any products
from their shopping cart in order to display the product information out back at them. I was running into an issue where
I wasn't sure how to display the product details until I remembered about the different 'JOIN' clause in MySql and was
able to easily use that in order to achieve what I was looking for.

<br>
<div align="center">
<b>Thank you for taking the time out to check out my application!</b>


</div>