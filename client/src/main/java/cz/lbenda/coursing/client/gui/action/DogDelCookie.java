/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.dto.Dog;
import org.openide.nodes.Node;

/** Cookie for removing dog
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface DogDelCookie extends Node.Cookie {
  Dog[] removedDogs();
}
