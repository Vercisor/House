#House Simulation by Davide Gamba
Smart Home Simulator was a coursework originally assigned in COMP1202 - the Programming I module
at the University of Southampton.

##Coursework Aims

- Understand how to construct basic classes and implement methods.
- Are able to take pseudo-code descriptions and construct working Java code from them.
- Can write a working program to perform a complex task.
- Have an understanding of object oriented programming.
- Can correctly use polymorphism and I/O.
- Can write code that it is understandable and conforms to good coding practice

##Specification

The aim of this coursework was to construct a simple simulation of the energy consumption- and energy generation- of a smart house. In the house are a number of appliances which consume/generate electricity or consume water when they operate, meters that record the amount of water and electricity consumed/generated and a battery which stores excess electricity if the house happens to generate more than it needs.

##How to run the Smart Home Simulator

Once you have downloaded all java classes and the configuration file ("house.txt" must be in the same folder), compile House.java from terminal:

> javac House.java

Once compiled, run the program with these arguments:

>java House "NameOfYourFile".txt 7 3

where the two numbers, 7 and 3 represent the number of days and the number of hours.
These parameters can be changed by the user as they please. 

The program also works with both 0 days as parameter (given that it runs for at least one hour) or with 0 hours as parameter, if it runs for at least 1 day

so it is possible to run:

>java House "NameOfYourFile".txt 0 3

to run the simulation for only 3 hours

or 

>java House "NameOfYourFile".txt 2 0

to run the simulation for 2 days
