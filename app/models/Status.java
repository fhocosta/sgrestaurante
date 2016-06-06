package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_status")
public class Status extends Model {

    @Id
    private long id;
    @Column(name="descricao")
    private String descricao;

    public Status() {
    }

    public static Finder<Long, Status> find = new Finder<Long, Status>(Status.class);

}
