//generate all prime numbers between two given numbers
//sieve of Erastothenes

package com.company;
import java.util.*;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.floor;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        List<Integer> highs = new ArrayList<>();
        List<Integer> lows = new ArrayList<>();
        int a, b;
        //Get Test cases
        for(int i = 0; i < testCases; i++){
            a = sc.nextInt();
            b = sc.nextInt();
            lows.add(a);
            highs.add(b);
        }
        for(int i = 0; i < testCases; i++){
            primesInRange(lows.get(i), highs.get(i));
            System.out.println();
        }
    }

    private static void primesInRange(int low, int high) {
        // Compute all primes smaller or equal to
        // square root of high using simple sieve
        int limit = (int) (floor(sqrt(high)) + 1);
        List<Integer> prime = new ArrayList<>();
        simpleSieve(limit, prime);

        // Count of elements in given range
        int n = high - low + 1;

        Boolean[] mark = new Boolean[n + 1];
        Arrays.fill(mark, Boolean.FALSE);
        for(int i = 0; i < prime.size(); i++){
            int loLim = (int) (floor(low / prime.get(i)) * prime.get(i));
            if(loLim < low)
                loLim += prime.get(i);
            if(loLim == prime.get(i))
                loLim += prime.get(i);
            for(int j = loLim; j <= high; j += prime.get(i))
                mark[j - low] = true;
        }

        for(int i = low; i <= high; i++)
            if(!mark[i - low] && i != 1)
                System.out.println(i);
    }

    private static void simpleSieve(int limit, List<Integer> prime) {
        Boolean[] mark = new Boolean[limit + 1];
        Arrays.fill(mark, Boolean.FALSE);
        for(int i = 2; i <= limit; ++i){
            if(!mark[i]){
                prime.add(i);
                for(int j = i; j <= limit; j += i)
                    mark[j] = true;
            }
        }
    }
}