package Controllers;

import Models.Usuario;
import Controllers.util.JsfUtil;
import Controllers.util.JsfUtil.PersistAction;
import Models.UsuarioFacade;
import Models.UsuarioFacadeLocal;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
    
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Models.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;

    public UsuarioController() {
    }
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
     //Uso el metodo del archivo facade
    public String iniciarSesion(){
        //creamos un objeto de tipo usuario
        Usuario us;
        String redireccion = null;
        try{
            //aqui aplicamos el metodo iniciarSesion que nos devuelve una persona de acuerdo
            //a los datos capturados
            us = EJBUsuario.iniciarSesion(usuario);
            //Primero, si los campos del logueo son conrrectos se hace la redireccion
            //Segundo, hay que validar cual es el rol de nuestro ususario
            if(us != null){
                if(us.getTipodeusuarioidTipodeusuario().getNombre().equals("instructor")){
                    redireccion = "tamplateEntitys";
                }
            }else{
                //si no, quiere decir que esa persona no esta registrada en el sistema
                //usamos un faces context para dar una notificacion
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o contraseña invalidos","Error"));
            }
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error interno","Error"));
        }
        return redireccion;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Lang/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Lang/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Lang/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}
