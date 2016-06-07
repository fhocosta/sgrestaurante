package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_sugestao")
public class Sugestao extends Model {

    @Id
    private long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @ManyToOne
    private Usuario usuario;

    public Sugestao() {
    }

    public static Finder<Long, Sugestao> find = new Finder<Long, Sugestao>(Sugestao.class);

    public static List<Sugestao> all() {
        return find.all();
    }

    public static Sugestao create(Sugestao sugestao) {
        sugestao.save();
        return sugestao;
    }

    public static Sugestao update(Long id, Sugestao s) {
        Sugestao sugestao = find.byId(id);

        if (s.getTitulo() != null) sugestao.setTitulo(s.getTitulo());
        if (s.getDescricao() != null) sugestao.setDescricao(s.getDescricao());
        if (s.getUsuario() != null) sugestao.setUsuario(s.getUsuario());

        return create(sugestao);
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
