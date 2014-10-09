/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.dto.Breed;
import org.openide.nodes.Node;

/**
 *
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface BreedDelCookie extends Node.Cookie {
  Breed removedBreed();
}
