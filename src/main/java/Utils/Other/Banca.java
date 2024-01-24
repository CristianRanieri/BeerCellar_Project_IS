package Utils.Other;

public class Banca {
    public static boolean effettuaPagamento(Pagamento pagamento){
        if(pagamento.getCvv().equals("000"))
            return false;
        else
            return true;
    }
}
