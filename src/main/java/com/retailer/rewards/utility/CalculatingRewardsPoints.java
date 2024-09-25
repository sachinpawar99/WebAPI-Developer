package com.retailer.rewards.utility;

import com.retailer.rewards.entities.Transactions;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalculatingRewardsPoints {
    public Map<String, Object> getTotalRewardsPoints(List<Transactions> transactionsList) {

        List<Transactions> filteredTransactions = filterRecentTransactions(transactionsList);

        return calculatingCustomerPoints(filteredTransactions);
    }

    // Method to filter transactions to only the last three months
    private List<Transactions> filterRecentTransactions(List<Transactions> transactionsList) {

        LocalDate currentDate = LocalDate.now();

        LocalDate threeMonthsAgo = currentDate.minusMonths(3).with(TemporalAdjusters.firstDayOfMonth());

        List<Transactions> recentTransactions = transactionsList.stream()
                .filter(transaction -> transaction.getTransactionDate().isAfter(threeMonthsAgo) ||
                        transaction.getTransactionDate().isEqual(threeMonthsAgo))
                .collect(Collectors.toList());

        return recentTransactions.isEmpty() ? transactionsList : recentTransactions;
    }
    private Map<String, Object> calculatingCustomerPoints(List<Transactions> transactionsList) {
        // This map holds the reward points for each month.
        Map<Month, Integer> monthlyPoints = new HashMap<>();

        int totalPoints = 0;

        for (Transactions transaction : transactionsList) {

            // Calculate points for each transaction.
            int points = calculatingPoints(transaction.getTransactionsAmount());

            Month transactionMonth = transaction.getTransactionDate().getMonth();

            monthlyPoints.put(transactionMonth, monthlyPoints.getOrDefault(transactionMonth, 0) + points);

            totalPoints += points;
        }
        // Convert the Month enum to its string
        Map<String, Integer> monthlyPointsByName = new HashMap<>();
        for (Map.Entry<Month, Integer> entry : monthlyPoints.entrySet()) {
            monthlyPointsByName.put(entry.getKey().getDisplayName(TextStyle.FULL, Locale.ENGLISH), entry.getValue());
        }

        Map<String, Object> totalPointCalculation = new HashMap<>();
        totalPointCalculation.put("totalPoints", totalPoints);
        totalPointCalculation.put("monthlyPoints", monthlyPointsByName);

        return totalPointCalculation;
    }

    private int calculatingPoints(double amount) {
        int points = 0;

        if (amount >= 100) {
            points += (amount - 100) * 2;
            points += 50;
        }
        else if (amount >= 50) {
            points += (amount - 50);
        }
        return points;
    }
}

