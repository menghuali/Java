# Java Streams
For perofmance consideration, the collection framework is not the right place to implement map/filter/reduce. That's why Java introduces Stream API.

Creating a Stream object doesn't duplicate data. By construction, a stream is empty.

Two kinds of Stream API
* Methods create another stream, aka intermediate methods
* Methods produce a result, aka terminal methods

What is Stream?
* an implementation of map/filter/reduce
* a stream doesn't carry any data
* intermediate methods & terminal methods

You are not allowed to operate a stream twice!

### Flat Mapping
