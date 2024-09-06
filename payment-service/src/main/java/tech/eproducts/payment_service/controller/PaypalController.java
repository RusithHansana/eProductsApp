package tech.eproducts.payment_service.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.eproducts.payment_service.model.PaymentRecord;
import tech.eproducts.payment_service.service.PaypalService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    private final PaypalService paypalService;

    /**
     * Displays the home page of the application, which is the page where the user
     * can create a new payment.
     *
     * @return The name of the template to display, which is "index".
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Creates a new payment and redirects the user to the PayPal approval URL.
     * <p>
     * This function is called when the user submits the payment form in the home
     * page.
     * It creates a new payment using the <code>paypalService</code> and redirects
     * the user
     * to the PayPal approval URL.
     * <p>
     * If the payment creation fails, it redirects the user to the error page.
     *
     * @param method      The payment method, which is "Paypal".
     * @param amount      The payment amount.
     * @param currency    The payment currency.
     * @param description The payment description.
     * @return The redirect view to the PayPal approval URL or the error page.
     */
    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description) {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(
                    Double.valueOf(amount),
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl);

            paypalService.savePaymentRecord(payment, "created");

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        return new RedirectView("/payment/error");
    }

    /**
     * Redirects the user to the success page after the payment is executed.
     * <p>
     * The payment is executed using the <code>paypalService</code> and the
     * payment ID and payer ID are passed as request parameters.
     * <p>
     * If the payment execution fails, it logs the error and redirects the user
     * to the success page.
     *
     * @param paymentId The payment ID.
     * @param payerId   The payer ID.
     * @return The redirect view to the success page.
     */
    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                paypalService.savePaymentRecord(payment, "approved");
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        return "paymentSuccess";
    }

    /**
     * Redirects the user to the cancel page when the payment is cancelled.
     * <p>
     * This function is called when the user cancels the payment.
     * <p>
     * It simply redirects the user to the "paymentCancel" page.
     *
     * @return The redirect view to the cancel page.
     */
    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paymentCancel";
    }

    /**
     * Redirects the user to the error page when the payment fails.
     * <p>
     * This function is called when the payment fails.
     * <p>
     * It simply redirects the user to the "paymentError" page.
     *
     * @return The redirect view to the error page.
     */
    @GetMapping("/payment/error")
    public String paymentError() {
        return "paymentError";
    }

    @GetMapping("/payments")
    @ResponseBody
    public List<PaymentRecord> getAllPayments() {
        return paypalService.getAllPaymentRecords();
    }
}
