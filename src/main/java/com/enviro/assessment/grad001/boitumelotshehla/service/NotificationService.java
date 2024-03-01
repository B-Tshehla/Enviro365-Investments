package com.enviro.assessment.grad001.boitumelotshehla.service;

import java.math.BigDecimal;

/**
 * The NotificationService interface provides methods for sending withdrawal notifications.
 * This includes notifying users about their withdrawal transactions with relevant details.
 */
public interface NotificationService {

    /**
     * Sends a withdrawal notification to a user with the specified details.
     *
     * @param firstName        The first name of the user receiving the notification.
     * @param email            The email address of the user receiving the notification.
     * @param withdrawalAmount The amount withdrawn in the transaction.
     * @param currentBalance   The current balance after the withdrawal.
     * @param closingBalance   The closing balance after the withdrawal transaction is complete.
     */
    void sendWithdrawalNotification(String firstName, String email, BigDecimal withdrawalAmount,
                                    BigDecimal currentBalance, BigDecimal closingBalance);
}
