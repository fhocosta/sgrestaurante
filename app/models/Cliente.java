package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.DocFlavor;

/**
 * Created by fhocosta on 31/05/16.
 */

@Entity
@Table(name = "tbl_clientes")
public class Cliente extends Usuario {

    @Id
    private long id;
    @Column(name = "receber_marketing")
    private boolean receberMarketing;

    public Cliente() {}

    public static Finder<Long, Cliente> find = new Finder<Long, Cliente>(Cliente.class);

}
