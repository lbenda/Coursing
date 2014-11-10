/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
