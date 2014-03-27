#EPGPlanner Refactoring

##Brief Summary
======
Most of the refactoring is done to the "formatter" classes. "SynopsisFormatter" has been made deprecated and a new package called "synopsis" has been created to include all the logics of formatting recording synopses.

##Refactoring Rationale

Basically the formatting logic has been restructured into the following two main components:

1. "SynopsisProducer" classes which are specified by interface "SynopsisProducer". These are the main classes that are used to produce recording synopses. 

It's responsible to define how synopses of a recording should be organized (but not now it should be formatted). The reason that it's defined is because the original formatter class ("SynopsisFormatter") is quite rigid in terms of representing the model of the synopses. It is now possible to have different number of pages of synopses and they are not necessarily been models as "pages", for example, synopsis could be modeled into "screens" depends on the device on which the synopsis is viewed. Each instance of "producer" class is given a "formatter" class (described next) to actually generate the synopsis.

2. "SynopsisFormatter" which are represented by "ManualRecordingSynopsisFormattingHelper" and "ScheduledRecordingSynopsisFormattingHelper" classes respectively. These classes are responsible to generate the synopsis texts and they are currently tightly coupled with the actual recording types since each type of recording requires different format for synopses.

There are also some general refactoring done to the recording classes to improve the code quality and readability, e.g. convert constants into enumerations. JUnit tests has also been updated to test the new "Producer" classes.

##Possible Further Improvements

1. The way that recording classes are constructed is not very maintainable. The constructors requires too many parameters. It could be difficult to extend these classes if more information needs to be added and it also impacts the readability of the code. A new class (possibly a "Synopsis" class) could be created to aid this.

2. More tests should be created to covering the new formatting classes. 

3. The structure of the new "formatting" classes could be improved to reduce duplicates.