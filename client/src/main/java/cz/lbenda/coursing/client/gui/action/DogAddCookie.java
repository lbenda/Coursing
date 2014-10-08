/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import java.awt.event.ActionEvent;
import org.openide.nodes.Node;

/** Node cookie for adding new dog
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface DogAddCookie extends Node.Cookie {
  void addNewDog(ActionEvent ev);
}
