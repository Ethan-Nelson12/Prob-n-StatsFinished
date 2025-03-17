import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

/*
  A collection of statistical and probability functions.
*/
public class StatsLibrary {

    // Calculates the standard deviation of a list of Double values.
    // It computes the average, variance (using n-1), and returns the square root of the variance as an int.
    public static int standeredDev(ArrayList<Double> data) {
        int sum = 0;             // Sum of all numbers
        int length = data.size(); // Total count of numbers
        int standeredDev = 0;    // Sum of squared differences

        // Sum all numbers in the list
        for (Double d : data) {
            sum += d;
        }
        
        // Compute the average (mean)
        int avg = sum / length;
        
        // Compute the sum of squared differences from the mean
        for (Double d : data) {
            standeredDev += Math.pow(d - avg, 2);
        }
        
        // Calculate the variance using (n-1) degrees of freedom
        int varience = standeredDev / (length - 1);
        double mydub = (double) varience;
        
        // Return the square root of the variance 
        return (int) Math.sqrt(mydub);
    }

    // Calculates the mean of an array of integers.
    public double getMean(int[] userInputNumbers) {
        int total = 0;  // Sum of all numbers
        for (int singleNumber : userInputNumbers) {
            total += singleNumber;
        }
        // Divide by the number of elements 
        double result = (double) total / userInputNumbers.length;
        return result;
    }

    // Calculates the median of an array of integers.
    public double getMedian(int[] a) {
        double median;
        // If the array length is even, the median is the average of the two middle numbers
        if (a.length % 2 == 0) {
            median = ((double) a[a.length / 2] + (double) a[a.length / 2 - 1]) / 2;
        } else {
            // If odd, the median is the middle element
            median = (double) a[a.length / 2];
        }
        return median;
    }

    // Finds the mode in an array of doubles.
    public double getMode(double a[]) {
        double maxValue = 0, maxCount = 0;
        // Loop through each element in the array
        for (int i = 0; i < a.length; ++i) {
            int count = 0;
            // Count how many times a[i] appears in the array
            for (int j = 0; j < a.length; ++j) {
                if (a[j] == a[i])
                    ++count;
            }
            // Update the mode if this element appears more frequently than the previous maximum
            if (count > maxCount) {
                maxCount = count;
                maxValue = a[i];
            }
        }
        return maxValue;
    }

    // Calculates the factorial of a number using BigInteger to handle large numbers.
    private static BigInteger factorial(int num) {
        BigInteger fact = BigInteger.ONE;  // Start at 1
        for (int i = 2; i <= num; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    // Calculates the number of permutations P(n, r) = n! / (n-r)!.
    public static BigInteger permutations(int n, int r) {
        if (r > n) return BigInteger.ZERO;
        return factorial(n).divide(factorial(n - r));
    }

    // Calculates the number of combinations C(n, r) = n! / (r!(n-r)!).
    public static BigInteger combinations(int n, int r) {
        if (r > n) return BigInteger.ZERO;
        return factorial(n).divide(factorial(r).multiply(factorial(n - r)));
    }

    // Calculates the conditional probability P(A|B) = P(A and B) / P(B).
    public double conditionalProbability(double pAAndB, double pB) {
        double cProb = pAAndB / pB;
        return cProb;
    }

    // Checks if events are dependent. Returns true if the conditional probabilities do not match the unconditional ones.
    public boolean isDependent(double pA, double pB, double pAGivenB, double pBGivenA, double pAAndB) {
        if (pAGivenB != pA) {
            return true;
        } else if (pBGivenA != pB) {
            return true;
        } else if (pAAndB != pA * pB) {
            return true;
        } else {
            return false;
        }
    }

    // Checks if events are independent. Returns true if the conditional probabilities match the unconditional ones.
    public boolean isIndependent(double pA, double pB, double pAGivenB, double pBGivenA, double pAAndB) {
        if (pAGivenB == pA) {
            return true;
        } else if (pBGivenA == pB) {
            return true;
        } else if (pAAndB == pA * pB) {
            return true;
        } else {
            return false;
        }
    }

    // Calculates the binomial probability distribution.
    // Formula: C(n, y) * p^y * q^(n-y)
    public double binomialProbabilityDistribution(double p, double q, int n, int y) {
        double binomial = combinations(n, y).doubleValue() * Math.pow(p, y) * Math.pow(q, n - y);
        return binomial;
    }

    // Calculates the geometric probability distribution.
    // Formula: q^(y-1) * p, where y is the trial number of the first success.
    public double geometricProbabilityDistribution(double q, double p, int y) {
        return Math.pow(q, y - 1) * p;
    }

    // Calculates the hypergeometric probability using BigInteger and BigDecimal.
    // Formula: P(X = k) = [C(K, k) * C(N-K, n-k)] / C(N, n)
    public static BigDecimal hypergeometricProbabilityBI(int N, int K, int n, int k) {
        // Calculate numerator: C(K, k) * C(N-K, n-k)
        BigInteger comb1 = combinations(K, k);
        BigInteger comb2 = combinations(N - K, n - k);
        BigInteger numerator = comb1.multiply(comb2);
        // Calculate denominator: C(N, n)
        BigInteger denominator = combinations(N, n);
        // Convert to BigDecimal for division with scale 10 and HALF_UP rounding mode
        BigDecimal numBD = new BigDecimal(numerator);
        BigDecimal denomBD = new BigDecimal(denominator);
        return numBD.divide(denomBD, 10, RoundingMode.HALF_UP);
    }

    // Calculates the negative binomial probability.
    // Formula: P(N = n) = C(n-1, k-1) * p^k * (1-p)^(n-k)
    // where n is the trial on which the kth success occurs.
    public static BigDecimal negativeBinomialProbability(int k, int n, BigDecimal p) {
        // Ensure that the trial number is at least the number of successes
        if (n < k) {
            return BigDecimal.ZERO;
        }
        // Calculate binomial coefficient: C(n-1, k-1)
        BigInteger binom = combinations(n - 1, k - 1);
        // Calculate p raised to the power k (success probability)
        BigDecimal pPow = p.pow(k);
        // Calculate (1-p) raised to the power (n-k) (failure probability)
        BigDecimal oneMinusP = BigDecimal.ONE.subtract(p);
        BigDecimal oneMinusPPow = oneMinusP.pow(n - k);
        // Multiply the binomial coefficient and the probability terms together
        BigDecimal binomBD = new BigDecimal(binom);
        return binomBD.multiply(pPow).multiply(oneMinusPPow)
                      .setScale(10, RoundingMode.HALF_UP);
    }

        public static void main(String[] args) {
            StatsLibrary test = new StatsLibrary();

            // --- Test standeredDev ---
            ArrayList<Double> data = new ArrayList<>();
            data.add(10.0);
            data.add(12.0);
            data.add(23.0);
            data.add(23.0);
            data.add(16.0);
            data.add(23.0);
            data.add(21.0);
            data.add(16.0);
            int stdDev = standeredDev(data);
            System.out.println("Standard Deviation: " + stdDev);
    
            // --- Test getMean ---
            int[] numbers = {1, 2, 3, 4, 5, 6};
            double mean = test.getMean(numbers);
            System.out.println("Mean: " + mean);
    
            // --- Test getMedian ---
            double median = test.getMedian(numbers);
            System.out.println("Median: " + median);
    
            // --- Test getMode ---
            double[] arr = {1.1, 2.2, 2.2, 3.3, 4.4};
            double mode = test.getMode(arr);
            System.out.println("Mode: " + mode);
    
            // --- Test factorial ---
            BigInteger fact5 = factorial(5);
            System.out.println("Factorial of 5: " + fact5); // Expected: 120
    
            // --- Test permutations ---
            BigInteger perms = permutations(5, 3);
            System.out.println("Permutations P(5,3): " + perms);
    
            // --- Test combinations ---
            BigInteger combs = combinations(5, 3);
            System.out.println("Combinations C(5,3): " + combs);
    
            // --- Test conditionalProbability ---
            double condProb = test.conditionalProbability(0.15, 0.3);
            System.out.println("Conditional Probability (P(A|B)) [0.15 / 0.3]: " + condProb);
    
            // --- Test isDependent and isIndependent ---
            // Using sample probabilities: pA=0.5, pB=0.3, pAGivenB=0.6, pBGivenA=0.4, pAAndB=0.2
            boolean dependent = test.isDependent(0.5, 0.3, 0.6, 0.4, 0.2);
            boolean independent = test.isIndependent(0.5, 0.3, 0.6, 0.4, 0.2);
            System.out.println("Is Dependent? " + dependent);
            System.out.println("Is Independent? " + independent);
    
            // --- Test binomialProbabilityDistribution ---
            // For n=10 trials, probability p=0.5 of success (q=0.5 failure) and y=3 successes.
            double binomProb = test.binomialProbabilityDistribution(0.5, 0.5, 10, 3);
            System.out.println("Binomial Probability Distribution (n=10, y=3, p=0.5): " + binomProb);
    
            // --- Test geometricProbabilityDistribution ---
            // For y=4 (first success on the 4th trial), with p=0.5 and q=0.5.
            double geomProb = test.geometricProbabilityDistribution(0.5, 0.5, 4);
            System.out.println("Geometric Probability Distribution (first success on trial 4, p=0.5): " + geomProb);
    
            // --- Test hypergeometricProbabilityBI ---
            // For a population of N=50, successes K=10, number of draws n=5, observed successes k=2.
            BigDecimal hyperProb = hypergeometricProbabilityBI(50, 10, 5, 2);
            System.out.println("Hypergeometric Probability (N=50, K=10, n=5, k=2): " + hyperProb);
    
            // --- Test negativeBinomialProbability ---
            // For k=3 successes on the n=7th trial with success probability p=0.5.
            BigDecimal negBinomProb = negativeBinomialProbability(3, 7, new BigDecimal("0.5"));
            System.out.println("Negative Binomial Probability (k=3, n=7, p=0.5): " + negBinomProb);
        }
    
        
    }

