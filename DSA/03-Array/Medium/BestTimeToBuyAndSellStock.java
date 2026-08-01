// Best Time to Buy and Sell Stock
// Given an array prices where prices[i] is the stock price on day i,
// return the maximum profit by buying once and selling once.

class BestTimeToBuyAndSellStock {

    // ------------------------------------------------------------
    // Approach 1: Brute Force
    // ------------------------------------------------------------
    // Try every possible buy day.
    // For each buy day, try every possible sell day.
    // Keep track of the maximum profit.
    //
    // Time Complexity: O(N²)
    // Space Complexity: O(1)
    public static int maxProfitBruteForce(int[] prices) {

        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }

    // ------------------------------------------------------------
    // Approach 2: Optimal
    // ------------------------------------------------------------
    // Keep track of the minimum price seen so far.
    // For each day, calculate the profit if sold today and update
    // the maximum profit.
    //
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static int maxProfitOptimal(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int currentProfit = 0;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {

            // Update the minimum buying price seen so far.
            minPrice = Math.min(minPrice, prices[i]);

            // Profit if the stock is sold today.
            currentProfit = prices[i] - minPrice;

            // Update the maximum profit.
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println("Brute Force: " + maxProfitBruteForce(prices));
        System.out.println("Optimal: " + maxProfitOptimal(prices));
    }
}