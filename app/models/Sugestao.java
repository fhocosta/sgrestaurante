package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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

    public static Sugestao create(Map<String, String> form) {
        Sugestao sugestao = new Sugestao();
        if(form.get("titulo") != null){
            sugestao.setTitulo(form.get("titulo"));
        }
        if(form.get("descricao") != null){
            sugestao.setDescricao(form.get("descricao"));
        }

        if(form.get("usuario") != null && !form.get("usuario").isEmpty()){
            Long id = Long.parseLong(form.get("usuario"));
            Usuario usuario = Usuario.find.byId(id);
            if (usuario != null){
                sugestao.setUsuario(usuario);
            }
        }

        return create(sugestao);


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
