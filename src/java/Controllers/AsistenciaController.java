package Controllers;

import Models.Asistencia;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Models.AsistenciaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("asistenciaController")
@SessionScoped
public class AsistenciaController implements Serializable {

    @EJB
    private Models.AsistenciaFacade ejbFacade;
    private List<Asistencia> items = null;
    private Asistencia selected;
    //La variable de tipo user fue creada por que quise hacerlo como en el repositorio
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AsistenciaController() {
    }

    public Asistencia getSelected() {
        return selected;
    }

    public void setSelected(Asistencia selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AsistenciaFacade getFacade() {
        return ejbFacade;
    }

    public Asistencia prepareCreate() {
        selected = new Asistencia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Lang/Bundle").getString("AsistenciaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Lang/Bundle").getString("AsistenciaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Lang/Bundle").getString("AsistenciaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Asistencia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    //creacion de la lista para guardar las asistencias dependiendo del usuario
    public List<Asistencia> getItemsByAprendiz(){
        items = null;
        UsuarioController usuarioC = new UsuarioController();
        //
        items = getFacade().getAsistenciasAprendizByIdUsuario(usuarioC.getUsuarioSesion().getIdUsuario());
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Lang/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Lang/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Asistencia getAsistencia(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Asistencia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Asistencia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Asistencia.class)
    public static class AsistenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsistenciaController controller = (AsistenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asistenciaController");
            return controller.getAsistencia(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Asistencia) {
                Asistencia o = (Asistencia) object;
                return getStringKey(o.getIdAsistencia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Asistencia.class.getName()});
                return null;
            }
        }

    }

}
