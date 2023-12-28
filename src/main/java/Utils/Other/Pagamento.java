package Utils.Other;

public class Pagamento {

    public Pagamento() {
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValorePagamento() {
        return valorePagamento;
    }

    public void setValorePagamento(double valorePagamento) {
        this.valorePagamento = valorePagamento;
    }

    private String numeroCarta;
    private String cvv;
    private String data;
    private double valorePagamento;
}
