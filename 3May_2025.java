// Prime List GFG Problem
class Solution {
    static final int MAX = 100000;
    static boolean[] isPrime = new boolean[MAX + 1];
    static ArrayList<Integer> primes = new ArrayList<>();

    static {
        // Sieve of Eratosthenes
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        // Store primes
        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) primes.add(i);
        }
    }

    Node primeList(Node head) {
        Node curr = head;
        while (curr != null) {
            curr.val = getClosestPrime(curr.val);  // FIXED: changed data -> val
            curr = curr.next;
        }
        return head;
    }

    private int getClosestPrime(int num) {
        int low = 0, high = primes.size() - 1;
        int closest = primes.get(0);
        int minDiff = Math.abs(num - closest);

        while (low <= high) {
            int mid = (low + high) / 2;
            int prime = primes.get(mid);
            int diff = Math.abs(num - prime);

            if (diff < minDiff || (diff == minDiff && prime < closest)) {
                minDiff = diff;
                closest = prime;
            }

            if (prime < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return closest;
    }
}
