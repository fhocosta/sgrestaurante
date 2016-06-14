package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 31/05/16.
 */

@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario {

    @Column(name = "receber_marketing")
    private boolean receberMarketing;

    public Cliente() {
    }

    public static Finder<Long, Cliente> find = new Finder<Long, Cliente>(Cliente.class);

    public static List<Cliente> all() {
        return find.all();
    }

    public static Cliente create(Cliente cliente) {
        cliente.save();
        return cliente;
    }

    public static Cliente update(Long id, Cliente c) {
        Cliente cliente = find.byId(id);
        Usuario.update(cliente.getId(), c);
        if (c.isReceberMarketing()) cliente.setReceberMarketing(c.isReceberMarketing());
        return create(cliente);
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public boolean isReceberMarketing() {
        return receberMarketing;
    }

    public void setReceberMarketing(boolean receberMarketing) {
        this.receberMarketing = receberMarketing;
    }

}
