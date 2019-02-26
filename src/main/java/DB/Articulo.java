package DB;

import java.util.Date;

public class Articulo {
    private long id;
    private String titulo;
    private String cuerpo;
    private Usuario autor;
    private Date fecha;
    private Comentario listaComentarios;
    private Etiqueta listaEtiquetas;

    public Articulo(long id, String titulo, String cuerpo, Usuario autor, Date fecha, Comentario listaComentarios, Etiqueta listaEtiquetas) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
        this.listaComentarios = listaComentarios;
        this.listaEtiquetas = listaEtiquetas;
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

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Comentario getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(Comentario listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    public Etiqueta getListaEtiquetas() {
        return listaEtiquetas;
    }

    public void setListaEtiquetas(Etiqueta listaEtiquetas) {
        this.listaEtiquetas = listaEtiquetas;
    }
}
