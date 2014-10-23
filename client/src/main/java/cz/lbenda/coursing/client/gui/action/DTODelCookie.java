/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.dto.NumberLine;
import cz.lbenda.coursing.service.AbstractDTOService;
import org.openide.nodes.Node;

/** Cookie for delete number line
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface DTODelCookie<T extends NumberLine> extends Node.Cookie {
  AbstractDTOService<T> numberLineService();
  T removedNumberLine();
}
