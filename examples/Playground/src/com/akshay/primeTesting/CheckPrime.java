package com.akshay.primeTesting;

import java.util.Arrays;

import javax.lang.model.type.PrimitiveType;
import javax.swing.Box.Filler;

public class CheckPrime {

	static boolean[] primes=new boolean[10000000]; 
	//set up the primesieve
	public static void fillSieve() {
		long startTime = System.nanoTime();
	    Arrays.fill(primes,true);        // assume all integers are prime.
	    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
	    for (int i=2;i<primes.length;i++) {
	        //if the number is prime, 
	        //then go through all its multiples and make their values false.
	        if(primes[i]) {
	            for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	            }
	        }
	    }
	    long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed Time for fillseive(): "+ elapsedTime);
	}
	
	public static boolean isPrime(int n) {
		return primes[n];
		//if (n == 1) n = Long.MAX_VALUE;
		/*long startTime = System.nanoTime();
		boolean flag = true;
		
		if (n % 2 == 0) {
			flag = false;
		}
		
		for(long i = 3; i*i <= n; i+=2) {
			if (n % i == 0) {
				flag = false;
			}
		}
		
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
	    System.out.println("Elapsed Time for isPrime(" + n + ") method: "+ elapsedTime);
		return flag;*/
	}
	
	public static void main(String[] args) {
		fillSieve();
		
		System.out.println("isPrime(23): " + isPrime(23));
		System.out.println("isPrime(100): " + isPrime(100));
		System.out.println("isPrime(7): " + isPrime(7));
		System.out.println("isPrime(67): " + isPrime(67));
		System.out.println("isPrime(29): " + isPrime(29));
		//System.out.println("isPrime(29): " + isPrime(29));
		System.out.println("isPrime(10,00,000): " + isPrime(1000000));
		//System.out.println("isPrime(100,00,00,000): " + isPrime(1000000000));
		//System.out.println("isPrime(100,00,00,009): " + isPrime(1000000009));
		//System.out.println("isPrime(9,223,372,036,854,775,807): " + isPrime(1));
	}
	
}
