package Maestros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AccesoDB.AccesoDB;
import MaestrosSpec.CrudServiceSpec;
import MaestrosSpec.RowMapper;
import Modelos.ModeloClient;

public class client implements CrudServiceSpec<ModeloClient>, RowMapper<ModeloClient> {

    private final String SQL_SELECT_ACTIVE = "SELECT * FROM client WHERE status='A'";
    private final String SQL_SELECT_INACTIVE = "SELECT * FROM client WHERE status='I'";
    private final String SQL_SELECT_ID = "SELECT * FROM client WHERE status='A' AND id=?";
    private final String SQL_SELECT_LIKE = "SELECT * FROM client WHERE name LIKE ? AND last_name LIKE ? AND status='A'";
    private final String SQL_INSERT = "INSERT INTO client (name, last_name, type_document, number_document, email, cell_phone, status) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "UPDATE client SET name=?, last_name=?, type_document=?, number_document=?, email=?, cell_phone=?, status=? WHERE id=?";
    private final String SQL_DELETE = "UPDATE client SET status='I' WHERE id=?";
    private final String SQL_RESTORE = "UPDATE client SET status='A' WHERE id=?";
    private final String SQL_ELIMINATE = "DELETE FROM client WHERE id=?";

    @Override
    public List<ModeloClient> getActive() {
        List<ModeloClient> lista = new ArrayList<>();
        try (Connection cn = AccesoDB.getConnection();
             PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_ACTIVE);
             ResultSet rs = pstm.executeQuery();) {
            while (rs.next()) {
                ModeloClient bean = mapRow(rs);
                lista.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<ModeloClient> getInactive() {
        List<ModeloClient> lista = new ArrayList<>();
        try (Connection cn = AccesoDB.getConnection();
             PreparedStatement pstm = cn.prepareStatement(SQL_SELECT_INACTIVE);
             ResultSet rs = pstm.executeQuery();) {
            while (rs.next()) {
                ModeloClient bean = mapRow(rs);
                lista.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public ModeloClient getForId(String id) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ModeloClient bean = null;
        try {
            cn = AccesoDB.getConnection();
            pstm = cn.prepareStatement(SQL_SELECT_ID);
            pstm.setInt(1, Integer.parseInt(id));
            rs = pstm.executeQuery();
            if (rs.next()) {
                bean = mapRow(rs);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return bean;
    }

    @Override
    public List<ModeloClient> get(ModeloClient bean) {
        Connection cn = null;
        List<ModeloClient> lista = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ModeloClient item;
        String name, last_name;
        name = "%" + UtilService.setStringVacio(bean.getName()) + "%";
        last_name = "%" + UtilService.setStringVacio(bean.getLast_name()) + "%";
        try {
            cn = AccesoDB.getConnection();
            pstm = cn.prepareStatement(SQL_SELECT_LIKE);
            pstm.setString(1, name);
            pstm.setString(2, last_name);
            rs = pstm.executeQuery();
            while (rs.next()) {
                item = mapRow(rs);
                lista.add(item);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }

    @Override
    public void insert(ModeloClient bean) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int filas;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            pstm = cn.prepareStatement(SQL_INSERT);
            pstm.setString(1, bean.getName());
            pstm.setString(2, bean.getLast_name());
            pstm.setString(3, bean.getType_document());
            pstm.setString(4, bean.getNumber_document());
            pstm.setString(5, bean.getEmail());
            pstm.setString(6, bean.getCell_phone());
            pstm.setString(7, bean.getStatus());
            filas = pstm.executeUpdate();
            pstm.close();
            if (filas != 1) {
                throw new SQLException("Error, verifique sus datos e inténtelo nuevamente.");
            }
            cn.commit();
        } catch (Exception e) {
            try {
                cn.rollback();
                cn.close();
            } catch (Exception e2) {
            }
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(ModeloClient bean) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int filas;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            pstm = cn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, bean.getName());
            pstm.setString(2, bean.getLast_name());
            pstm.setString(3, bean.getType_document());
            pstm.setString(4, bean.getNumber_document());
            pstm.setString(5, bean.getEmail());
            pstm.setString(6, bean.getCell_phone());
            pstm.setString(7, bean.getStatus());
            pstm.setInt(8, bean.getId());
            filas = pstm.executeUpdate();
            pstm.close();
            if (filas != 1) {
                throw new SQLException("Error, verifique sus datos e inténtelo nuevamente.");
            }
            cn.commit();
        } catch (Exception e) {
            try {
                cn.rollback();
                cn.close();
            } catch (Exception e2) {
            }
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(String id) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int filas = 0;
        try {
            // Inicio de Tx
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            pstm = cn.prepareStatement(SQL_DELETE);
            pstm.setInt(1, Integer.parseInt(id));
            filas = pstm.executeUpdate();
            pstm.close();
            if (filas != 1) {
                throw new SQLException("No se pudo eliminar el cliente.");
            }
            cn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
    }

    @Override
    public void restore(String id) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int filas = 0;
        try {
            // Inicio de Tx
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            pstm = cn.prepareStatement(SQL_RESTORE);
            pstm.setInt(1, Integer.parseInt(id));
            filas = pstm.executeUpdate();
            pstm.close();
            if (filas != 1) {
                throw new SQLException("No se pudo restaurar el cliente.");
            }
            cn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
    }

    @Override
    public void eliminate(String id) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int filas = 0;
        try {
            // Inicio de Tx
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            pstm = cn.prepareStatement(SQL_ELIMINATE);
            pstm.setInt(1, Integer.parseInt(id));
            filas = pstm.executeUpdate();
            pstm.close();
            if (filas != 1) {
                throw new SQLException("No se pudo eliminar el cliente.");
            }
            cn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
    }

    @Override
    public ModeloClient mapRow(ResultSet rs) throws SQLException {
        ModeloClient bean = new ModeloClient();
        bean.setId(rs.getInt("id"));
        bean.setName(rs.getString("name"));
        bean.setLast_name(rs.getString("last_name"));
        bean.setType_document(rs.getString("type_document"));
        bean.setNumber_document(rs.getString("number_document"));
        bean.setEmail(rs.getString("email"));
        bean.setCell_phone(rs.getString("cell_phone"));
        bean.setStatus(rs.getString("status"));
        return bean;
    }
}
