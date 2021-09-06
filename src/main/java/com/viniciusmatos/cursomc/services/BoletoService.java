package com.viniciusmatos.cursomc.services;

import com.viniciusmatos.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanciaDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanciaDoPedido);
        cal.add(Calendar.DAY_OF_MONTH,7);
        pagto.getDataVencimento(cal.getTime());
    }
}
