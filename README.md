# CustomerSorter


A simple solution written in java to read a full list of customers records provided via a link to a JSON encoded text file and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

Using the first formula from the Wikipedia article found at https://en.wikipedia.org/wiki/Great-circle_distance to calculate distance, and the GPS coordinates for the Intercom Dublin office -- 53.339428, -6.257664.

## Usage (Mac & PC)

```
$ gradle installDist
$ cd build/install/IntercomCustomerSorter/bin
$ ./IntercomCustomerSorter pathOrUrlToEncodedJSON       
```
### Or

```
$ gradle installDist
$ cd build/install/IntercomCustomerSorter/bin
$ ./IntercomCustomerSorter.bat pathOrUrlToEncodedJSON       
```
## Help

```
use help/h/? as argument instead of pathOrUrlToEncodedJSON
```

## Please Note

Coordinates for dublin office have been set and can be altered in the properties.config file.

## Author

Opeyemi Disu
https://github.com/opedisu
opedisu@gmail.com

