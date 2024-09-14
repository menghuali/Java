# Java Lambda
## Functional Interface
* Lambda implements the function intefaces.
* Lambda is not anonymous class.

### What is functional interface
* Interface
* Only one bastract method
* Default and static methods doesn't count
* Object methods, like *equals*, doesn't count

### java.util.function
* 40 interfaces
* Organized in 4 categories: Supplier, Consumer, Predicate, Function
* Runnable interface
* Functional annotation is not mandatory, like Runnable. It make you can use Lambda with old java code.

## Performance
* Lambda compile code is different and uses *invokedynamic*
* Performance are 60x better
* Avoid autoboxing, which converts betweem primitive types and wrapper classes. Following example boxing and unboxing impact the performance.
* For primitive types, use specialized function interface instead of using generic ones. E.g. use IntSupplier over Supplier.****

```java
Comparator<Integer> cmp = (a, b) -> Integer.compare(a, b);
int compared = cmp.compare(10, 20);
```
