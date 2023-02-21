# 3. Checking for Null Using Annotations

> in java there are no standard annotations to prevent nulls

## How To Choose An Annotation Library

Where can you use the annotation?

- Java 8 type annotations
    - `List<@NonNull String>`
- common contexts
    - method parameters and return values
    - fields
    - local variables
    - packages

At what point is the null check performed?

- static code analysis
    - detect null values at compile time
    - generate compiler error if NonNull restriction is violated
- runtime
    - throws exception if a field or parameter gets a null value

## Spring Annotations

- `org.springframework.lang` package
    - `@NonNull`: a specific element, a parameter or return value, cannot be null
    - `@Nullable`: can be null
    - `@NonNullApi`: (package level) sets default behavior for parameters and return values
    - `@NonNullFields`: (package level) sets same behavior, but only for fields

IntelliJ can be configured to produce warnings and errors from these annotations:

- File > Settings > Editor > Java > Probable bugs > @NonNull/@Nullable problems > Configure Annotations
- `+` sign to add "NonNull" from `org.springframework.lang`

## Bean Validation Annotations

- standard validation specification defined by
    - JSR 303 (first version)
    - JSR 380 (second version)
- validate objects by using a set of constraints as annotations
    - a Validator checks if the constraints are satisfied

Examples of constraints (that can be combined):

- `@NonNull`
- `@Size`
- `@Min/@Max`
- `@PositiveOrZero `

_Hibernate Validator_ is the implementation of these specifications

- **Does it only validate objects of the domain model?** No, it validates objects in all layers
- **Are `@NotNull` and `@Column(nullable=false)` equivalent?** No
    - `@Column` is part of the JPA spec
        - if hibernate creates the table, it adds a _not null_ constraint to the **database** column
    - `@NotNull` is part of the Bean Validation spec
        - triggers a validation during an update or persist lifecycle event
        - validation happens at the application level

### Demo Notes

- for a Spring Boot app use `spring-boot-starter-validation` (contains dependency to `hibernate-validator`)
- for DTOs
    - can use `@NotNull` for fields and for list items: `List<@NotNull String> mylist`
- validation for web layer (eg. `@RestController`)
    - add `@Valid` to the parameters we want to validate (parameter class should have validation annotations)
- validation for `@Service` class
    - in addition to `@Valid`, we need to add the `@Validation` annotation to the class
- validation for the persistence layer
    - you don't need to use `@Valid` or `@Validation`, only annotate the entity classes w/ bean validation annotations
    - bean validation is only triggered by hibernate once the entity manager is flushed

## Project Lombok

- helps reduce boilerplate code
- works as an annotation post-processor
    - reads annotations during pre-compilation process to auto-generate code and inject it into the application
- includes a `@NonNull` annotation for parameters of methods and constructors
    - simple if statement that throws an exception if the parameter is null
    - also used w/ the `@Data` annotation
    - by default a `NullPointerException` will be thrown, otherwise
        - IllegalArgumentException
        - AssertionError

### Demo Notes

- enable support for annotation processing in IntelliJ
    - Settings > Build, Execution, Deployment > Compiler > AnnotationProcessors
        - [x] enable annotation processing
    - Settings > Plugins
        - search for lombok plugin
        - install & restart intellij
- add lombok dependency to your project with a `provided` scope: `<scope>provided</scope>`
    - required to compile the code, but not needed at runtime
- we can create `lombok.config` files in any package and
    - put configuration directives that apply to that package and its children
        - an example would be to configure the exception type thrown:
            - ```lombok.config
              lombok.nonnull.exceptiontype = IllegalArgumentException            
              ```
    - flag usage of `@NonNull` annotations as a `warning` or as an `error`
        - with `lombok.nonNull.flagUsage`
        - when set to `error`, it will fail the compilation b/c of null errors
        - otherwise it will display a warning