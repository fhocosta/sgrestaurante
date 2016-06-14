package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_status")
public class Status extends Model {

    @Id
    private long id;
    @Column(name = "descricao")
    private String descricao;

    public Status() {
    }

    public static Finder<Long, Status> find = new Finder<Long, Status>(Status.class);

    public static List<Status> all() {
        return find.all();
    }

    public static Status create(Status status) {
        status.save();
        return status;
    }

    public static Status update(Long id, Status s) {
        Status status = find.byId(id);

        if (s.getDescricao() != null) status.setDescricao(s.getDescricao());

        return create(status);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
