/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.admin.controlador;

import com.mycompany.admin.modelo.persona;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.atmosphere.util.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Lorena Garzon
 * @author Alejandra Hernandez
 */

@ManagedBean
@RequestScoped
public class personasControl {
    
    private persona usuarios = new persona();
    //lista para guardar datos y eliminarlas
    private static List<persona> listaUsuarios = new ArrayList();
    private static List<persona> listaUsuariosEliminada = new ArrayList();

    public personasControl() {
    }

    public persona getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(persona usuarios) {
        this.usuarios = usuarios;
    }

    public List<persona> getListaUsuarios() {
        return listaUsuarios;
    }

    public  void setListaUsuarios(List<persona> listaUsuarios) {
        personasControl.listaUsuarios = listaUsuarios;
    }
    
    
    public void agregarLista(){
        //***
        personasControl.listaUsuarios.add(usuarios);
        notificarPUSH();
    }
     public String Eliminarlista(){
         
         for(persona p: listaUsuarios){
             if(p.isSelec()){
                 listaUsuariosEliminada .add(p);
             }
             
             if(listaUsuariosEliminada.isEmpty()){
                 listaUsuarios.removeAll(listaUsuariosEliminada);
                 
             }
             
         }
         
         notificarPUSHElim();
         return "welcomePrimefaces";
         
         
         
    }
    //notificacion de datos agregados
    public void notificarPUSH(){
        String nuevoElemento = "Nuevo elemento en la lista";
        String agregar = "Se agrego a la lista";
        String CHANEL = "/notify";
        
        EventBus eventBus =  EventBusFactory.getDefault().eventBus();
        try {
            eventBus.publish(CHANEL,new FacesMessage(StringEscapeUtils.escapeJava(nuevoElemento),StringEscapeUtils.escapeJava(agregar)) );
        } catch (Exception ex) {
            Logger.getLogger(personasControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //notificacion de datos eliminados    
     public void notificarPUSHElim(){
        String nuevoElemento = "Elemento Eliminado";
        String agregar = "Se ha eliminado el elemento";
        String CHANEL = "/notify";
        
        EventBus eventBus =  EventBusFactory.getDefault().eventBus();
        try {
            eventBus.publish(CHANEL,new FacesMessage(StringEscapeUtils.escapeJava(nuevoElemento),StringEscapeUtils.escapeJava(agregar)) );
        } catch (Exception ex) {
            Logger.getLogger(personasControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
