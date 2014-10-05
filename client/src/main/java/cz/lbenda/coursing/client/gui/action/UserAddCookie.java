/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import java.awt.event.ActionEvent;
import org.openide.nodes.Node;

/** Cookies for adding user to application.
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface UserAddCookie extends Node.Cookie {
  void userAdd(ActionEvent ev);
}
