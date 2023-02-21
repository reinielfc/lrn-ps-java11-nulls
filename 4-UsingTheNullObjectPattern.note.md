# 4. Using the Null Object Pattern

a.k.a active nothing, or stub

- instead of using a null reference to represent the absence of an object,
- it uses an object that implements the expected interface but does nothing,
- hiding the details from its collaborators

examples of null objects:

- string: empty string, `s = "";`
- collection: empty collection, `l = Collections.emptyList();`
- for numbers: a constant value, `n = 0`

> avoids using null values

## Relation to Other Patterns

- doesn't work like a _proxy_
- can be the _State pattern_ if it can transform into something else
- can be seen as a special case of the _Strategy pattern_

## Implementing the Null Object Pattern [[GITHUB][branch.gh]]

- abstract class that defines the behavior for all objects of this type
- the Null Object is a subclass of the abstract class

## Criticisms of the Null Object Pattern

- an incorrect implementation can make bugs harder to detect
  - Null objects can fail slowly
  - do not implement it just to avoid null checks
  - best suited when a default value can be assigned ot a default action can be taken
- creating a proper null object may not be easy
  - may have to implement lots of classes
  - should it do nothing? or should it fail with an exception?
    - instead of using a null object it might be better to just throw an exception
  - what if you still have to check for the null object?
  - what if the parent class is final? you cannot creat a null object for it

[branch.gh]: https://github.com/reinielfc/lrn-ps-java11-nulls/tree/4-UsingTheNullObjectPattern
