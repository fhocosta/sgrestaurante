package models;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by fhocosta on 06/06/16.
 */
@Entity
@Table(name = "tbl_comanda")
public class Comanda extends Model {

    @Id
    private long id;
    @Column(name = "data_abertura")
    private Date dataAbertura;
    @Column(name = "data_fechamento")
    private Date dataFechamento;
    @Column(name = "total")
    private float total;
    @Column(name = "aberta")
    private boolean aberta;

    //TODO CADE O CLIENTE ?????
    //TODO CADE O FUNCIONARIO ?????
    //TODO CADE A MESA ????
    //TODO A ENTIDADE QUE RECEBE UM ID ESTRANGEIRO DEVE MAPEA-LO, POR PRECAUÇÃO

}
