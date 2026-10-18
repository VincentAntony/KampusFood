package kampusfood.payment;

// Adaptee: simulasi pustaka pembayaran lama yang tidak boleh diubah.
public class OldPayGateway {
    public boolean makePayment(String account, int amountInCents) {
        System.out.printf(
            "[OldPayGateway] Pembayaran akun %s sebesar %d cents diproses.%n",
            account, amountInCents
        );
        return amountInCents > 0 && !"fail".equalsIgnoreCase(account);
    }
}
