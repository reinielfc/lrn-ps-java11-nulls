# 2. Working with Reference Types and Nulls

> It's better to not overcomplicate things. Sometimes a simple null check will be enough.

- null is a value that indicates that a reference doesn't refer to an object
- the JLS defines a Null type (but it cannot be used)
- the type of the literal value null is Null
  - that's why `null instanceof Object` returns false

## Traditional Ways of Dealing with Nulls

### Assertions

```java
class MyClass {
    public boolean isBookReadyForPublication(Book book) {
        assert book != null : "Book is null";
        // ...
    }
}
```

### If/Else Statements

```java
class MyClass {
    public boolean isBookReadyForPublication(Book book) {
        if (book != null) {
            // ...
        } else {
            // book object is null
        }
    }
}
```

### java.util.Objects Class

```java
import java.util.Objects;

class MyClass {
    public boolean isBookReadyForPublication(Book book) {
        Objects.requireNonNull(book, "Book is null");
    }
}
```

```java
import java.util.Objects;

class MyClass {
    public boolean isBookReadyForPublication(Book book) {
        if (Objects.nonNull(book)) {
            // ...
        } else {
            // book object is null
        }
    }
}
```

```java
import java.util.Objects;

class MyClass {
    public boolean isBookReadyForPublication(Book book) {
        if (Objects.isNull(book)) {
            // book object is null
        } else {
            // ...
        }
    }
}
```

### Try/Catch 

```java
class MyClass {
    public boolean isBookReadyForPublication(Book book) {
        try {
            // use book object
        } catch (NullPointerException e) {
            // book object was null
        }
    }
}
```

## Best Practices for Data That You Don't Control

- external data (we don't control)
- internal data (we do control)
- check for null in the outer layers / entry points of the app
- document public or exposed elements (w/ javadoc)
  - methods: describe the contract (post- and preconditions, parameters, return values)
- always validate parameters at the beginning of the method (fail fast)
- after detecting an invalid null value:
  -  not always be the right choice: replacing the null value w/ a default value
  - throw an exception: NullPointerException or IllegalArgumentException (be explicit)
    - pick one and use it consistently

## Best Practices For Data That You Control

- no need to check for null in every method
  - never pass null as an argument
    - use primitives instead of wrapper classes
    - for optional parameters, overload the method w/ different sets of parameters
  - never return null
    - return an empty collection
    - return a Null Object pattern
    - return an Optional type
