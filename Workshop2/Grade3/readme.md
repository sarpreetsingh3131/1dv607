#The current folder contains programme which is fulfilling the requirement three and four.

##Simple search
Simple search is using strategy pattern.
In order to implement new search criteria,

1. You need to add a name of new search to the enumeration. (View is depending on the enumeration to show available options)
2. You need to create a concrete class which realises ISimpleSearchStrategy (interface).
3. You need to add a method in search strategy returning that new class you created.
4. Now in the view side, you need to add one more switch case, and ask for corresponding input.

## Data validation and error handling
We had previously had strategy to check all erroneous inputs from the view part, but now we decided to categorise the errors into two group: logical error and input error. As for input error we define it as handling over right type of value; if it requires numbers then it accepts only numbers.
As View class is the place where it receives an input from user, we decided to assign responsibility to check the type of input from view.
We defined checking whether the data seems to be reasonable or not to be checking its logical validity, and we assigned the responsibility on the model side as we model defines right type of input. In case of incorrect input, model throws an exception which is later handled by model side.

##Complex search
Complex search is also using strategy pattern.
We have interpreted a complex search by nesting results of previous simple search. Therefore, it performs comparison of two search results, and nest it by 'AND' or 'OR' operation.
