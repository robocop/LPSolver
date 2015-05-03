A linear programming solver in Java.

To install the solver:
----------------------

   git clone https://github.com/robocop/LPSolver.git
   make

To launch it
------------

   ./toto file.lp [OPTIONS]

   Example:
   ./toto lp_examples/mobilePhone.lp -rationals -debug


To produce a pdf (only, of course, for small instances)
-------------------------------------------

   ./pdf file.lp [OPTIONS]


The OPTIONS are the following:

   --double  -> use doubles to do the computation
   --rationals -> use rationals
   --latex -> output a latex file




Features
--------


This solver supports arbitrary division ring.
A division ring is defined in the DivisionRing module. To create a new division ring, you should implement the
interface DivisionRing<T>.


The simplex algorithm is implemented in the SimplexAlgorithm module.
The file SimplexBase.java implements the second phase of the algorithm (when the initial dictionary represent an
effective solution).
The file Simplex.java implements the first phase (find an initial solution or return an empty domain).



The main data structure is defined in the module LinearCombination: it represents the dictionary entries.
It based on native arrays (which are quite efficient, even in Java).
It's a sparse structure for inequalities, and a compact structure for variables.
Thank to this structure, the solver supports linear program with 100 variables and 10 000 inequalities.



The parser (module Parser) is realised with ANLTR (http://www.antlr.org/) and a Visitor Pattern
(see ParserVisitor.java).


The classes Item, Variables, Bound, Bounds, Inequality, Inequalities, Objective, LinearProgram are a network of classes
in order to represent the parsed linear program.

The class LinearProgramToSimplexEncapsulation takes a LinearProgram and make it uniform, ie on the following form:

Maximize z0+z1 * x1 + ...

s.t.:
a0 + a1 * x1 + ... <= c0
b0 + b1 * x1 + ... <= c1
...

with:
x1 >= 0
x2 >= 0
...


In order to do that, LinearProgramToSimplexEncapsulation:
-remove useless inequalities
-introduce new variables when initial variables are unbounded
-transform a minimization problem into a maximization one.


