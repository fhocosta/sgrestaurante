package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_reserva")
public class Reserva extends Model {

    @Id
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    @ManyToOne(cascade = CascadeType.ALL)
    private Funcionario funcionario;
    @Column(name = "data")
    private Date data;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "quantidade_convidados")
    private int quantidadeConvidados;
    @ManyToOne(cascade = CascadeType.ALL)
    private Mesa mesa;

    public Reserva() {
    }

    public static Finder<Long, Reserva> find = new Finder<Long, Reserva>(Reserva.class);

    public static List<Reserva> all() {
        return find.all();
    }

    public static Reserva create(Map<String, String> form) {
        Reserva reserva = new Reserva();

        if(form.get("cliente") != null){
            Long id = Long.parseLong(form.get("cliente"));
            Cliente cliente = Cliente.find.byId(id);
            if(cliente != null){
                reserva.setCliente(cliente);
            }
        }

        if(form.get("atendente") != null){
            Long id = Long.parseLong(form.get("atendente"));
            Funcionario funcionario = Funcionario.find.byId(id);
            if(funcionario != null){
                reserva.setAtendente(funcionario);
            }
        }

        if(form.get("mesa") != null){
            Long id = Long.parseLong(form.get("mesa"));
            Mesa mesa = Mesa.find.byId(id);
            if(mesa != null){
                reserva.setMesa(mesa);
            }
        }

        if(form.get("data") != null){
            String string = form.get("data");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) reserva.setData(date);
        }

        if(form.get("quantidadeConvidados") != null){
            reserva.setQuantidadeConvidados(Integer.parseInt(form.get("quantidadeConvidados")));
        }

        if(form.get("observacao") != null){
            reserva.setObservacao(form.get("observacao"));
        }

        return create(reserva);
    }

    public static Reserva create(Reserva reserva) {
        reserva.save();
        return reserva;
    }

    public static Reserva update(Long id, Map<String, String> form) {
        Reserva reserva = find.byId(id);

        if(form.get("cliente") != null){
            Long idCliente = Long.parseLong(form.get("cliente"));
            Cliente cliente = Cliente.find.byId(idCliente);
            if(cliente != null){
                reserva.setCliente(cliente);
            }
        }

        if(form.get("funcionario") != null){
            Long idAtendente = Long.parseLong(form.get("funcionario"));
            Funcionario funcionario = Funcionario.find.byId(idAtendente);
            if(funcionario != null){
                reserva.setAtendente(funcionario);
            }
        }

        if(form.get("mesa") != null){
            Long idMesa = Long.parseLong(form.get("mesa"));
            Mesa mesa = Mesa.find.byId(idMesa);
            if(mesa != null){
                reserva.setMesa(mesa);
            }
        }

        if(form.get("data") != null){
            String string = form.get("data");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = formatter.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) reserva.setData(date);
        }

        if(form.get("quantidadeConvidados") != null){
            reserva.setQuantidadeConvidados(Integer.parseInt(form.get("quantidadeConvidados")));
        }

        if(form.get("observacao") != null){
            reserva.setObservacao(form.get("observacao"));
        }
        
        return create(reserva);

    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getAtendente() {
        return funcionario;
    }

    public void setAtendente(Funcionario atendente) {
        this.funcionario = funcionario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getQuantidadeConvidados() {
        return quantidadeConvidados;
    }

    public void setQuantidadeConvidados(int quantidadeConvidados) {
        this.quantidadeConvidados = quantidadeConvidados;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
