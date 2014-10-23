/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.node;

import java.util.Arrays;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DefaultChildFactory<T extends Node> extends ChildFactory<T> {
  private final T[] nodes;
  public DefaultChildFactory(T... nodes) {
    this.nodes = nodes;
  }
  @Override
  protected boolean createKeys(List<T> toPopulate) {
    toPopulate.addAll(Arrays.asList(nodes));
    return true;
  }

  @Override
  protected Node createNodeForKey(T key) {
    return key;
  }
}
