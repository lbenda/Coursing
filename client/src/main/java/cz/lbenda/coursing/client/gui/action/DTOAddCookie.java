/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.dto.DTO;
import cz.lbenda.coursing.service.AbstractDTOService;
import org.openide.nodes.Node;

/** Add number line cookie
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface DTOAddCookie extends Node.Cookie {
  AbstractDTOService numberLineService();
  void showForm(DTO numberLine);
}