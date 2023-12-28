package GestioneOrdini.Service;

import Utils.Other.Banca;
import Utils.Other.Pagamento;

public class PagamentoAdapter implements PagamentoService{
    @Override
    public boolean pagamento(Pagamento pagamento) {
        return Banca.effettuaPagamento(pagamento);
    }
}
