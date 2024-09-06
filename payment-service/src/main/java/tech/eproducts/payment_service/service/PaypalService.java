package tech.eproducts.payment_service.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import tech.eproducts.payment_service.model.PaymentRecord;
import tech.eproducts.payment_service.repository.PaymentRecordRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaypalService {

    private final APIContext apiContext;

    private final PaymentRecordRepository paymentRecordRepository;

    /**
     * Creates a new payment and returns the response from PayPal.
     * <p>
     * The payment is created with the given amount, currency, method, intent,
     * description and URLs.
     * <p>
     * The payment is created using the {@link APIContext} instance.
     * <p>
     * The method throws a {@link PayPalRESTException} if the payment creation
     * fails.
     *
     * @param total       The payment amount.
     * @param currency    The payment currency.
     * @param method      The payment method.
     * @param intent      The payment intent.
     * @param description The payment description.
     * @param cancelUrl   The URL that the user will be redirected to when they
     *                    cancel the payment.
     * @param successUrl  The URL that the user will be redirected to when the
     *                    payment is executed successfully.
     * @return The response from PayPal.
     * @throws PayPalRESTException If the payment creation fails.
     */
    public Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", total)); // 9.99$ - 9,99€

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    /**
     * Executes a payment using the payment ID and payer ID.
     *
     * @param paymentId The payment ID.
     * @param payerId   The payer ID.
     * @return The response from PayPal.
     * @throws PayPalRESTException If the payment execution fails.
     */
    public Payment executePayment(
            String paymentId,
            String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }

    public PaymentRecord savePaymentRecord(com.paypal.api.payments.Payment paypalPayment, String status) {
        PaymentRecord record = new PaymentRecord();
        record.setPaypalPaymentId(paypalPayment.getId());
        record.setAmount(Double.valueOf(paypalPayment.getTransactions().get(0).getAmount().getTotal()));
        record.setCurrency(paypalPayment.getTransactions().get(0).getAmount().getCurrency());
        record.setMethod(paypalPayment.getPayer().getPaymentMethod());
        record.setDescription(paypalPayment.getTransactions().get(0).getDescription());
        record.setStatus(status);
        return paymentRecordRepository.save(record);
    }

    public List<PaymentRecord> getAllPaymentRecords() {
        return paymentRecordRepository.findAllByOrderByIdDesc();
    }
}
