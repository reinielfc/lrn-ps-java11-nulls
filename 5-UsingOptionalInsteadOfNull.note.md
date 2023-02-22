# Using Optional Instead of Null

> sometimes, the absence of data is not an error

when using collections, return an empty collection instead of null to indicate the absence of data

```java
public List<Book> findBookByAuthor(int authorId) {
    //...
}
```

it's the same principle behind optional

- a list is a container for objects
- an optional is an immutable container that contains
  - a single object of type T or 
  - nothing at all (empty)

```java
public Optional<Book> findBookById(int id) {
    // ...
}
```

> if a method returns a collection there's no need to wrap it an optional, just an empty collection

## Creating an Optional

```java
// from a non-null object
Optional<Book> optionalBook = Optional.of(book); // NullPointerException will be thrown if null

// from an object that may hold a null value
Optional<Book> optionalBook = Optional.ofNullable(book);

// creating an empty optional
Optional<Book> optionalBook = Optional.empty();
```

## Unpacking a Value from an Optional

```java
// it can throw a NoSuchElementException
Book book = optionalBook.get(); // least safe method

// you can check if it has a value first, but this is no better than null-checking
if (optionalBook.isPresent()) {
    book = optionalBook.get();
} else {
    // ...    
}
```

```java
// to provide a default value
Book book = optionalBook.orElse(new Book());

// to provide a default value via a supplier
Book book = optionalBook.orElseGet(() -> new Book());
```

## Methods Similar to the Ones of the Stream API (Optional Type Good Practices)

> the best way to use Optional is through composition

### Filter



```java
Optional<T> filter(Predicate<? super T> predicate)
```

- takes a predicate, 
- if a value is present, and it matches the predicate, 
  - it returns the optional
  - otherwise it returns an _empty_ optional object

```java
service.findBookById(id)
        .filter(book -> book.getNumberOfPages() > 500)
        .ifPresent(book -> System.out.println("It is a long book"));
```

### Map

```java
Optional<U> map(Function<? super T, ? extends U> mapper)
```

```java
String title = service.findBookById(id)
        .map(book -> book.getTitle())
        .orElse("");
```

### FlatMap

```java
Optional<U> flatMap(Function<? super T, Optional<U> mapper)
```

if `book.getTitle()` it were to return an optional, we would have to use a flatMap

```java
String title = service.findBookById(id)
        .flatMap(book -> book.getTitle())
        .orElse("");
```

were we not to use it, the result would be an optional containing another optional

#### Using Optional through Composition

- always start from an Optional
- apply a chain of filter, map or flatMap methods
- Use orElse or orElseGet to Unwrap the value

### Good Practices

- never use `Optional.get()` unless you're sure the Optional is not empty
- generally don't use Optional in fields
  - the Optional class is not serializable
  - for truly optional fields, have a getter method return Optional
- don't use Optional as a method argument, not necessary
  - use methods of th Optional type itself
- DO use Optional as a return value 

## Things to Keep in Mind about the Optional Type

- optional is a Java class, so a reference to this type might be null
- optional doesn't address other important sources of NullPointerException
  - partially initialized objects
  - getting elements from collections or maps
- can make your code harder to read (more verbose)
  - Types: Optional<Book> vs Book
  - Checking: obj.isPresent() vs obj != null (not recommended way to use an optional)
- can convert a NullPointerException into another exception
