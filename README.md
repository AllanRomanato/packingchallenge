# Packing Challenge

Here you will find the packinging challenge developed in Java programming language, it was used the library `JUnit` for unit tests, it was used Maven, so for you to run this code is necessary to convert to a Maven project, since just the code is on Github.

### Important points

To create this logic it was used the concept of combinations to create all possible combinations before decide which one will be the best choice. For example.

For the following entry:

```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
```

The code will generate the following combinations, note that it is using the index which we have in the file, it is just a representation when the `toString()` method is called, but the whole object is in there and ready for the next method where it will be used to find the best match.

```
[]
[1]
[2]
[1, 2]
[3]
[1, 3]
[2, 3]
[1, 2, 3]
[4]
[1, 4]
[2, 4]
[1, 2, 4]
[3, 4]
[1, 3, 4]
[2, 3, 4]
[1, 2, 3, 4]
[5]
[1, 5]
[2, 5]
[1, 2, 5]
[3, 5]
[1, 3, 5]
[2, 3, 5]
[1, 2, 3, 5]
[4, 5]
[1, 4, 5]
[2, 4, 5]
[1, 2, 4, 5]
[3, 4, 5]
[1, 3, 4, 5]
[2, 3, 4, 5]
[1, 2, 3, 4, 5]
[6]
[1, 6]
[2, 6]
[1, 2, 6]
[3, 6]
[1, 3, 6]
[2, 3, 6]
[1, 2, 3, 6]
[4, 6]
[1, 4, 6]
[2, 4, 6]
[1, 2, 4, 6]
[3, 4, 6]
[1, 3, 4, 6]
[2, 3, 4, 6]
[1, 2, 3, 4, 6]
[5, 6]
[1, 5, 6]
[2, 5, 6]
[1, 2, 5, 6]
[3, 5, 6]
[1, 3, 5, 6]
[2, 3, 5, 6]
[1, 2, 3, 5, 6]
[4, 5, 6]
[1, 4, 5, 6]
[2, 4, 5, 6]
[1, 2, 4, 5, 6]
[3, 4, 5, 6]
[1, 3, 4, 5, 6]
[2, 3, 4, 5, 6]
[1, 2, 3, 4, 5, 6]
```
Those are the possible combinations between the package items, after we have it, now we just have to loop into it and figure out which one is the best match for our given criteria, pretty simple. The real deal is to create the combinations and for it I used the binary representation to accomplish it. Since inside the bits we have all the possible combinations, this is the code snippet that do this magic, the shift left operator is responsible to get the bits we need.

```Java
int size = listBean.size();
  for (int i = 0; i < (1 << size); i++) { //Shift Left operation
	  List<PackageBean> possibilities = new ArrayList<>();
		for (int j = 0; j < size; j++) {
		  if ((i & (1 << j)) != 0) { //Shift Left operation
			  possibilities.add(listBean.get(j));
			}
		}
	  allPossibilities.add(possibilities);
  }
```

Exemple of shift left operator
```
1<<0 = 1
1<<1 = 2
1<<3 = 4
1<<4 = 8
```

You can note that is how it works in bits and bytes.

Here you will find the full code which is documented with JavaDoc and some test cases, it was not used properties file since it has a small amount of constants.
