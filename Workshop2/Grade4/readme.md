#The current folder contains programme which is fulfilling the requirement three and four.

1. Simple search
In order to implement search function, we have added a method simpleSearch in our model class "Registry" which returns a list of 'Member'.
New types of criteria can be easily added by modifying this method; it takes Object as its parameter and performs search according to it.
We also have enum called 'SearchMode' so if a criteria is added in this enumeration and our 'simpleSearch' method it can be noticed from the view part, and even functionality will easily implement if view guarantees correct input type for the search.

2. Data validation and error handling
We had previously had strategy to check all erroneous inputs from the view part, but now we decided to categorise the errors into two group: logical error and input error. As for input error we define it as handling over right type of value; if it requires numbers then it accepts only numbers.
As View class is the place where it receives an input from user, we decided to assign responsibility to check the type of input from view.
We defined checking whether the data seems to be reasonable or not to be checking its logical validity, and we assigned the responsibility on the model side as we model defines right type of input. In case of incorrect input, model throws an exception which is later handled by model side.

3. Complex search
We have interpreted a complex search by nesting results of previous simple search. Therefore, it performs comparison of two search results, and nest it by 'AND' or 'OR' operation. It means complex search is as much as flexible as simple search is.

NOTE: GUI does not implement complex search function. It is only available in console.
