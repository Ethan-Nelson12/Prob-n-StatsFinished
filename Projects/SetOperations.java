package Projects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetOperations {

    // Returns the union of two ArrayLists
    public ArrayList<Integer> union(ArrayList<Integer> a1, ArrayList<Integer> a2) {
        // Create a new ArrayList starting with all elements from a1
        ArrayList<Integer> result = new ArrayList<>(a1);
        // Loop through each number in a2
        for (Integer num : a2) {
            // If result does not already contain the number add it
            if (!result.contains(num)) {
                result.add(num);
            }
        }
        // Return the combined list 
        return result;
    }

    // Returns the intersection of two lists
    public List<Integer> intersect(List<Integer> list1, List<Integer> list2) {
        // Create a new list by copying list1
        List<Integer> intersection = new ArrayList<>(list1);
        // Keep only the elements that are also in list2
        intersection.retainAll(list2);
        // Return the list
        return intersection;
    }

    // Returns the complement of subset in sample
    public List<Integer> complement(List<Integer> sample, List<Integer> subset) {
        // Create a new list by copying sample
        List<Integer> complementSet = new ArrayList<>(sample);
        // Remove all elements that are in subset from the copied list
        complementSet.removeAll(subset);
        // Return the list 
        return complementSet;
    }

    // Calculates the intersection probability for independent events
    public double independentIntersection(double probA, double probB) {
        // Multiply the probabilities because events are independent
        return probA * probB;
    }

    // Calculates the intersection probability for dependent events (P(A and B) = P(A)*P(B|A))
    public double dependentIntersection(double probA, double conditionalProbBGivenA) {
        // Multiply the probability of A with the conditional probability of B given A
        return probA * conditionalProbBGivenA;
    }

    // Calculates the union probability for mutually exclusive events (P(A or B) = P(A) + P(B))
    public double exclusiveUnion(double probA, double probB) {
        // Simply add the probabilities because events cannot happen together
        return probA + probB;
    }

    // Calculates the union probability for non-exclusive events (P(A or B) = P(A) + P(B) - P(A and B))
    public double nonExclusiveUnion(double probA, double probB, double intersectionProb) {
        // Subtract the overlap to avoid counting it twice
        return probA + probB - intersectionProb;
    }

    // The main method to test all of the above methods
    public static void main(String[] args) {
        // Create an instance of TestUtils for non-static methods
        SetOperations op = new SetOperations();

        // ---------------------------
        // Testing the union method
        // ---------------------------
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
        ArrayList<Integer> unionResult = op.union(list1, list2);
        System.out.println("Union: " + unionResult); // Expected: [1, 2, 3, 4, 5, 6]

        // ---------------------------
        // Testing the intersect method
        // ---------------------------
        // Note: We call intersect as a static method of TestUtils
        List<Integer> intersectResult = op.intersect(list1, list2);
        System.out.println("Intersection: " + intersectResult); // Expected: [3, 4]

        // ---------------------------
        // Testing the complement method
        // ---------------------------
        // Get elements in list1 that are not in list2
        List<Integer> complementResult = op.complement(list1, list2);
        System.out.println("Complement (list1 \\ list2): " + complementResult); // Expected: [1, 2]

        // ---------------------------
        // Testing the probability methods
        // ---------------------------
        double probA = 0.5;    // Example probability for event A
        double probB = 0.3;    // Example probability for event B

        // Testing independentIntersection
        double independentInt = op.independentIntersection(probA, probB);
        System.out.println("Independent Intersection (0.5 * 0.3): " + independentInt); // Expected: 0.15

        // Testing dependentIntersection (using conditional probability)
        double conditionalProbBGivenA = 0.4; // Example: probability of B given A
        double dependentInt = op.dependentIntersection(probA, conditionalProbBGivenA);
        System.out.println("Dependent Intersection (0.5 * 0.4): " + dependentInt); // Expected: 0.2

        // Testing exclusiveUnion
        double exclusiveU = op.exclusiveUnion(probA, probB);
        System.out.println("Exclusive Union (0.5 + 0.3): " + exclusiveU); // Expected: 0.8

        // Testing nonExclusiveUnion
        // Using independentInt (0.15) as the overlapping probability
        double nonExclusiveU = op.nonExclusiveUnion(probA, probB, independentInt);
        System.out.println("Non-exclusive Union (0.5 + 0.3 - 0.15): " + nonExclusiveU); // Expected: 0.65
    }
}