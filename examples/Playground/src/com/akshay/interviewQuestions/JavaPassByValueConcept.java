package com.akshay.interviewQuestions;

import java.util.ArrayList;


/* http://www.javaworld.com/article/2077424/learn-java/does-java-pass-by-reference-or-pass-by-value.html
 											OR 
The Java Spec says that everything in Java is pass-by-value. There is no such thing as "pass-by-reference" in Java.
 
The key to understanding this is that something like

Dog myDog;
is not a Dog; it's actually a pointer to a Dog.

What that means, is when you have

Dog myDog = new Dog("Rover");
foo(myDog);
you're essentially passing the address of the created Dog object to the foo method.

(I say essentially because Java pointers aren't direct addresses, but it's easiest to think of them that way)

Suppose the Dog object resides at memory address 42. This means we pass 42 to the method.

if the Method were defined as

public void foo(Dog someDog) {
    someDog.setName("Max");     // AAA
    someDog = new Dog("Fifi");  // BBB
    someDog.setName("Rowlf");   // CCC
}
let's look at what's happening.

the parameter someDog is set to the value 42
at line "AAA"
someDog is followed to the Dog it points to (the Dog object at address 42)
that Dog (the one at address 42) is asked to change his name to Max
at line "BBB"
a new Dog is created. Let's say he's at address 74
we assign the parameter someDog to 74
at line "CCC"
someDog is followed to the Dog it points to (the Dog object at address 74)
that Dog (the one at address 74) is asked to change his name to Rowlf
then, we return
Now let's think about what happens outside the method:

Did myDog change?

There's the key.

Keeping in mind that myDog is a pointer, and not an actual Dog, the answer is NO. myDog still has the value 42; it's still pointing to the original Dog.

It's perfectly valid to follow an address and change what's at the end of it; that does not change the variable, however.

Java works exactly like C. You can assign a pointer, pass the pointer to a method, follow the pointer in the method and change the data that was pointed to. However, you cannot change where that pointer points.

In C++, Ada, Pascal and other languages that support pass-by-reference, you can actually change the variable that was passed.

If Java had pass-by-reference semantics, the foo method we defined above would have changed where myDog was pointing when it assigned someDog on line BBB.

Think of reference parameters as being aliases for the variable passed in. When that alias is assigned, so is the variable that was passed in.
*/
public class JavaPassByValueConcept {

	public static void main(String[] args) {
		// Primitive Type Experiment
		Integer i = new Integer(4);
		integerChange1(i); // This func changes the value of "i" to 4
		System.out.println(i);
		integerChange2(i); // This func will not be able to change the value though :)
		System.out.println(i);
		
		// Collection Type Experiment
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("aks1");
		arr.add("aks2");
		System.out.println(arr.toString());
		arrayChange1(arr); // This func changes the array
		System.out.println(arr.toString());
		arrayChange2(arr); // This func will not change the array
		System.out.println(arr.toString());
	}
	

	private static void arrayChange2(ArrayList<String> arr) {
		// TODO Auto-generated method stub
		ArrayList<String> newarr = new ArrayList<String>();
		newarr.add("mani1");
		newarr.add("mani2");
		arr = newarr;
	}


	private static void arrayChange1(ArrayList<String> arr) {
		// TODO Auto-generated method stub
		arr.add("aksnew");
	}


	private static void integerChange2(Integer i) {
		// TODO Auto-generated method stub
		Integer a = new Integer(3);
		i = a;
	}

	private static void integerChange1(Integer i) {
		// TODO Auto-generated method stub
		int k = 4;
		i = k;
	}
	
}
