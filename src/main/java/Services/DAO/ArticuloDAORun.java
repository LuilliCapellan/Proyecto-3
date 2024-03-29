package Services.DAO;

import DB.Articulo;
import DB.Usuario;
import Services.DAO.Interfaces.ArticuloDAO;
import Services.Entitys.EtiquetaService;
import Services.Entitys.UsuarioService;
import Services.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticuloDAORun implements ArticuloDAO {

    @Override
    public void insert(Articulo e){
        Connection con = null;

        try	{

            con = Service.getInstancia().getConnection();
            String sql = "Insert into PUBLIC.ARTICULO(id, TITULO, CUERPO, AUTOR, FECHA) values(NEXTVAL('SECUENCIA_ARTICULO'), ?, ?, ?, ?); ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            //preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(1, e.getTitulo());
            preparedStatement.setString(2, e.getCuerpo());
            preparedStatement.setLong(3, e.getAutor().getId());
            preparedStatement.setDate(4, e.getFecha());

            preparedStatement.execute();


        } catch (Exception e1) {
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);
        }finally{

            try {
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);
            }
        }
    }

    @Override
    public void update(Articulo e) {

        Connection con = null;
        try {

            con = Service.getInstancia().getConnection();
            String sql = "UPDATE PUBLIC.ARTICULO u SET id = ?, TITULO = ?, CUERPO = ?, FECHA = ?  WHERE u.id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1, e.getId());
            preparedStatement.setString(2, e.getTitulo());
            preparedStatement.setString(3, e.getCuerpo());
            preparedStatement.setDate(4, e.getFecha());
            preparedStatement.setLong(5, e.getId());

            preparedStatement.executeUpdate();

        }catch (Exception e1){
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);
            }
        }

    }

    @Override
    public long getNextID(){
        Long next = null;
        Connection con = null;

        try{
            con = Service.getInstancia().getConnection();
            String sql = "SELECT CURRVAL('PUBLIC.SECUENCIA_ARTICULO')";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                next = resultSet.getLong(1);
            }
        }catch (Exception e1){
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (SQLException e1){
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);
            }
        }
        return next+1;
    }

    @Override
    public void delete(Articulo e) {
        Connection con = null;

        try {
            con = Service.getInstancia().getConnection();
            String sql = "delete from PUBLIC.ARTICULO u where u.id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, e.getId());

            preparedStatement.execute();

        }catch (Exception e1){
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e1) {
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e1);

            }
        }
    }

    @Override
    public List<Articulo> getAll() {
        List<Articulo> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.ARTICULO ORDER BY FECHA DESC ";
            con = Service.getInstancia().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            Set(list, resultSet);
        } catch (Exception e) {
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }

    private void Set(List<Articulo> list, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {

            Articulo articulo = new Articulo();
            articulo.setId(resultSet.getLong("id"));
            articulo.setTitulo(resultSet.getString("Titulo"));
            articulo.setCuerpo(resultSet.getString("Cuerpo"));
            SetArt(resultSet, articulo);


            list.add(articulo);
        }
    }

    private void SetArt(ResultSet resultSet, Articulo articulo) throws SQLException {
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = usuarioService.getById(resultSet.getLong("autor"));
        articulo.setAutor(usuario);
        articulo.setFecha(resultSet.getDate("fecha"));
    }

    @Override
    public List<Articulo> getbyAutor(long id) {
        List<Articulo> list = new ArrayList<>();
        Connection con = null;

        try {
            String sql = "select * from PUBLIC.ARTICULO WHERE AUTOR = ? ORDER BY FECHA DESC  ";
            con = Service.getInstancia().getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Set(list, resultSet);
        } catch (Exception e) {
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e);

            }
        }


        return list;
    }


    @Override
    public Articulo getById(long id) {

        Connection con = null;
        Articulo articulo = null;

        try {
            con = Service.getInstancia().getConnection();
            String sql = "SELECT * FROM PUBLIC.ARTICULO a WHERE a.ID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){


                articulo = new Articulo();
                articulo.setId(resultSet.getLong("id"));
                articulo.setTitulo(resultSet.getString("titulo"));
                articulo.setCuerpo(resultSet.getString("cuerpo"));

                SetArt(resultSet, articulo);
                EtiquetaService etiquetaService = new EtiquetaService();

            }

        }catch (Exception e){
            Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e);

        }finally {
            try {
                assert con != null;
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(ArticuloDAORun.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return articulo;
    }


}
